package com.wang.mall.front.service.impl;

import com.wang.mall.common.utils.SnowflakeIdWorker;
import com.wang.mall.front.component.CancelOrderSender;
import com.wang.mall.front.dao.OmsOrderDao;
import com.wang.mall.front.dao.OmsOrderItemDao;
import com.wang.mall.front.dao.PmsSkuStockDao;
import com.wang.mall.front.domain.ConfirmOrderResult;
import com.wang.mall.front.domain.OmsOrderInfoResult;
import com.wang.mall.front.domain.OrderParam;
import com.wang.mall.front.domain.OrderQueryParam;
import com.wang.mall.front.dto.OmsOrderWithItemDTO;
import com.wang.mall.front.exception.OrderNotFoundException;
import com.wang.mall.front.exception.OrderTimeOutException;
import com.wang.mall.front.exception.PmsSkuStockNotFoundException;
import com.wang.mall.front.exception.PmsSkuStockUnderStockException;
import com.wang.mall.front.factory.OrderTypeServiceFactory;
import com.wang.mall.front.service.FrontCacheService;
import com.wang.mall.front.service.OmsCartItemService;
import com.wang.mall.front.service.OmsOrderService;
import com.wang.mall.front.service.UmsMemberService;
import com.wang.mall.mapper.OmsOrderItemMapper;
import com.wang.mall.mapper.OmsOrderMapper;
import com.wang.mall.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 订单Service实现类
 *
 * @author 王念
 * @create 2020-03-22 17:07
 */
@Service
@Slf4j
public class OmsOrderServiceImpl implements OmsOrderService {
    @Autowired
    private UmsMemberService memberService;
    @Autowired
    private OmsCartItemService cartItemService;
    @Autowired
    private PmsSkuStockDao skuStockDao;
    @Autowired
    private OmsOrderMapper orderMapper;
    @Autowired
    private OmsOrderItemDao orderItemDao;
    @Autowired
    private SnowflakeIdWorker snowflakeIdWorker;
    @Autowired
    private OmsOrderItemMapper orderItemMapper;
    @Autowired
    private OmsOrderDao orderDao;
    @Autowired
    private CancelOrderSender cancelOrderSender;
    @Autowired
    private FrontCacheService frontCacheService;

    @Override
    public ConfirmOrderResult generateConfirmOrder(List<Long> ids) {
        //获取订单包含的商品
        List<OmsCartItem> cartItemList = cartItemService.list(ids);
        return ConfirmOrderResult.builder().cartItemList(cartItemList).calcAmount(total(cartItemList)).build();
    }

    @Override
    @Transactional
    public String generateOrder(OrderParam orderParam) {
        List<Long> ids = orderParam.getProductInfos().stream().map(OrderParam.ProductInfo::getProductSkuId).collect(Collectors.toList());
        //获取数据，同时获取的行加排它锁
        List<PmsSkuStock> skuStocks = skuStockDao.getPmsSkuListForUpdate(ids);
        //购买的商品信息
        List<OrderParam.ProductInfo> productInfos = orderParam.getProductInfos();

        if (CollectionUtils.isEmpty(productInfos) || CollectionUtils.isEmpty(skuStocks))
            throw new PmsSkuStockNotFoundException("商品不存在");
        //总价
        BigDecimal totalAmount = BigDecimal.ZERO;
        //需要删除缓存的商品id
        Set<Long> keys = new HashSet<>(orderParam.getProductInfos().size());
        //校验库存
        for (OrderParam.ProductInfo productInfo : productInfos) {
            keys.add(productInfo.getProductId());
            List<PmsSkuStock> filterSkuStockResultList = skuStocks.stream()
                    .filter(stock -> stock.getId().equals(productInfo.getProductSkuId()))
                    .collect(Collectors.toList());
            if (CollectionUtils.isEmpty(filterSkuStockResultList)) {
                throw new PmsSkuStockNotFoundException("商品sku不存在");
            }
            //将用户购买的数量与库存比较
            PmsSkuStock skuStock = filterSkuStockResultList.get(0);
            if (productInfo.getQuantity() > skuStock.getStock())
                throw new PmsSkuStockUnderStockException("商品库存不足");
            //计算总价
            totalAmount = totalAmount.add(total(productInfo.getQuantity(), skuStock.getPrice()));

        }
        //更新缓存
        frontCacheService.delProduct(keys);
        //更新库存
        skuStockDao.updatePmsSkuStock(orderParam.getProductInfos());
        //创建订单
        return createOrder(orderParam, skuStocks, totalAmount);
    }

    @Override
    public OmsOrderInfoResult getOrderInfoByOrderSn(String orderSn) {
        return orderDao.getOrderInfoByOrderSn(orderSn);
    }

    @Override
    @Transactional
    public void cancelOrderByOrderSn(String orderSn) {
        log.info("取消订单:{}", orderSn);
        OmsOrderExample example = new OmsOrderExample();
        example.createCriteria().andOrderSnEqualTo(orderSn);
        //先判断订单是否已经支付
        List<OmsOrder> omsOrderList = orderMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(omsOrderList))
            return;
        if ((!omsOrderList.get(0).getStatus().equals(0)))
            //订单状态不是待支付状态
            return;
        //修改订单状态为已关闭
        OmsOrder order = OmsOrder.builder().status(4).build();
        orderMapper.updateByExampleSelective(order, example);
        //返还库存
        OmsOrderItemExample orderItemExample = new OmsOrderItemExample();
        orderItemExample.createCriteria().andOrderSnEqualTo(orderSn);
        List<OmsOrderItem> orderItemList = orderItemMapper.selectByExample(orderItemExample);
        if (CollectionUtils.isEmpty(orderItemList))
            return;
        skuStockDao.cancelPmsSkuStock(orderItemList);

    }

    @Override
    public List<OmsOrderWithItemDTO> getOrderWithItemByMemberId(Integer orderType) {
        UmsMember currentMember = memberService.getCurrentMember();
        return OrderTypeServiceFactory.getOmsOrderTypeServiceByType(orderType).list(currentMember.getId());
    }

    @Override
    public Integer deleteOrder(Long orderId) {
        //将订单和订单商品标记为删除状态
        OmsOrder order = OmsOrder.builder().id(orderId).deleteStatus(1).build();
        return orderMapper.updateByPrimaryKeySelective(order);
    }

    @Override
    public String payOrder(String orderSn) {
        OmsOrderExample example = new OmsOrderExample();
        example.createCriteria().andOrderSnEqualTo(orderSn);
        //确认订单是否超时
        List<OmsOrder> orderList = orderMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(orderList))
            throw new OrderNotFoundException("订单不存在");
        OmsOrder order = orderList.get(0);
        if (order.getStatus() == 4)
            throw new OrderTimeOutException("订单已经超时");
        Date currentDate = new Date();
        orderMapper.updateByExampleSelective(
                OmsOrder.builder()
                        .status(1)
                        .payType(1)
                        .paymentTime(currentDate)
                        .modifyTime(currentDate).build(),
                example);
        return orderSn;
    }

    private String createOrder(OrderParam orderParam, List<PmsSkuStock> skuStocks, BigDecimal totalAmount) {
        //获取当前用户
        UmsMember currentMember = memberService.getCurrentMember();
        //0元
        BigDecimal zeroBigDecimal = new BigDecimal(0);
        //生成订单号
        String orderSn = generateOrderSn();
        OmsOrder order = OmsOrder.builder()
                .memberId(currentMember.getId())
                .orderSn(orderSn)
                .createTime(new Date())
                .couponId(0L)
                .memberUsername(currentMember.getUsername())
                .totalAmount(totalAmount)
                .payAmount(totalAmount)
                .freightAmount(zeroBigDecimal)
                .promotionAmount(zeroBigDecimal)
                .integrationAmount(zeroBigDecimal)
                .couponAmount(zeroBigDecimal)
                .discountAmount(zeroBigDecimal)
                .payType(0)
                .sourceType(0)
                .status(0)
                .orderType(0)
                .receiverName(orderParam.getAddress().getName())
                .receiverPhone(orderParam.getAddress().getPhoneNumber())
                .integration(0)
                .receiverProvince(orderParam.getAddress().getProvince())
                .receiverCity(orderParam.getAddress().getCity())
                .receiverRegion(orderParam.getAddress().getRegion())
                .receiverStreet(orderParam.getAddress().getStreet())
                .receiverDetailAddress(orderParam.getAddress().getDetailAddress())
                .note(orderParam.getNote())
                .deleteStatus(0)
                .useIntegration(0)
                .build();
        orderMapper.insert(order);
        //插入相关商品
        List<OmsOrderItem> items = new ArrayList<>(orderParam.getProductInfos().size());
        for (OrderParam.ProductInfo productInfo : orderParam.getProductInfos()) {
            PmsSkuStock skuStock = skuStocks.stream()
                    .filter(stock -> stock.getId().equals(productInfo.getProductSkuId()))
                    .collect(Collectors.toList()).get(0);
            OmsOrderItem item = OmsOrderItem.builder()
                    .orderId(order.getId())
                    .orderSn(orderSn)
                    .productId(productInfo.getProductId())
                    .productPic(productInfo.getProductPic())
                    .productName(productInfo.getProductName())
                    .productSn(productInfo.getProductSn())
                    .productPrice(skuStock.getPrice())
                    .productQuantity(productInfo.getQuantity())
                    .productSkuId(skuStock.getId())
                    .productSkuCode(skuStock.getSkuCode())
                    .productAttr(skuStock.getSpData())
                    .build();
            items.add(item);
        }
        orderItemDao.insertList(items);
        //TODO 此处应该发送延时消息取消订单
        sendDelayMessageCancelOrder(orderSn);
        return orderSn;
    }

    private void sendDelayMessageCancelOrder(String orderSn) {
        //取消时间 ->30分钟
        long delayTimes = 1000 * 60 * 30;
        //发送延时消息
        cancelOrderSender.sendMessage(orderSn, delayTimes);
    }

    /**
     * 生成订单号
     *
     * @return orderSn
     */
    private String generateOrderSn() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Date currentDate = new Date();
        return dateFormat.format(currentDate) + String.valueOf(snowflakeIdWorker.nextId());
    }

    /**
     * 计算总价
     *
     * @param quantity 购买数量
     * @param price    单价
     * @return 总价
     */
    private BigDecimal total(Integer quantity, BigDecimal price) {

        return new BigDecimal(quantity).multiply(price);

    }

    /**
     * 计算总价
     *
     * @param cartItems 选中的商品
     * @return 总价
     */
    private ConfirmOrderResult.CalcAmount total(List<OmsCartItem> cartItems) {

        BigDecimal total = BigDecimal.ZERO;
        if (!CollectionUtils.isEmpty(cartItems)) {
            for (OmsCartItem cartItem : cartItems) {
                total = total.add(cartItem.getPrice().multiply(new BigDecimal(cartItem.getQuantity())));
            }
        }
        return ConfirmOrderResult.CalcAmount.builder()
                .total(total)
                .promotion(BigDecimal.ZERO)
                .freight(BigDecimal.ZERO)
                .coupon(BigDecimal.ZERO)
                .build();
    }
}
