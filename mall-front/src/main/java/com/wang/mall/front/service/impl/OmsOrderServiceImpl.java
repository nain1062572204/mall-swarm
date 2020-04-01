package com.wang.mall.front.service.impl;

import com.wang.mall.common.utils.SnowflakeIdWorker;
import com.wang.mall.front.dao.OmsOrderItemDao;
import com.wang.mall.front.dao.PmsSkuStockDao;
import com.wang.mall.front.domain.ConfirmOrderResult;
import com.wang.mall.front.domain.OrderParam;
import com.wang.mall.front.exception.PmsSkuStockNotFoundException;
import com.wang.mall.front.exception.PmsSkuStockUnderStockException;
import com.wang.mall.front.service.OmsCartItemService;
import com.wang.mall.front.service.OmsOrderService;
import com.wang.mall.front.service.UmsMemberService;
import com.wang.mall.mapper.OmsOrderMapper;
import com.wang.mall.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 订单Service实现类
 *
 * @author 王念
 * @create 2020-03-22 17:07
 */
@Service
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

    @Override
    public ConfirmOrderResult generateConfirmOrder(List<Long> ids) {
        //获取订单包含的商品
        List<OmsCartItem> cartItemList = cartItemService.list(ids);
        return ConfirmOrderResult.builder().cartItemList(cartItemList).calcAmount(total(cartItemList)).build();
    }

    @Override
    @Transactional
    public void generateOrder(OrderParam orderParam) {
        List<Long> ids = orderParam.getProductInfos().stream().map(OrderParam.ProductInfo::getStockId).collect(Collectors.toList());
        //获取数据，同时获取的行加排它锁
        List<PmsSkuStock> skuStocks = skuStockDao.getPmsSkuListForUpdate(ids);
        //购买的商品信息
        List<OrderParam.ProductInfo> productInfos = orderParam.getProductInfos();

        if (CollectionUtils.isEmpty(productInfos) || CollectionUtils.isEmpty(skuStocks))
            throw new PmsSkuStockNotFoundException("商品不存在");
        //总价
        BigDecimal totalAmount = new BigDecimal("0");
        //校验库存
        for (OrderParam.ProductInfo productInfo : productInfos) {
            List<PmsSkuStock> filterSkuStockResultList = skuStocks.stream()
                    .filter(stock -> stock.getId().equals(productInfo.getStockId()))
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
        //更新库存
        skuStockDao.updatePmsSkuStock(orderParam.getProductInfos());
        //创建订单
        createOrder(orderParam, skuStocks, totalAmount);
        //添加订单包含的商品
    }

    private void createOrder(OrderParam orderParam, List<PmsSkuStock> skuStocks, BigDecimal totalAmount) {
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
                .note(orderParam.getNote())
                .deleteStatus(0)
                .useIntegration(0)
                .build();
        orderMapper.insert(order);
        //插入相关商品
        List<OmsOrderItem> items = new ArrayList<>(orderParam.getProductInfos().size());

        for (OrderParam.ProductInfo productInfo : orderParam.getProductInfos()) {
            PmsSkuStock skuStock = skuStocks.stream()
                    .filter(stock -> stock.getId().equals(productInfo.getStockId()))
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
                    .productSkuCode(skuStock.getSkuCode())
                    .productAttr(skuStock.getSpData())
                    .build();
            items.add(item);
        }
        orderItemDao.insertList(items);
    }

    /**
     * 生成订单号
     *
     * @return orderSn
     */
    private String generateOrderSn() {
        SnowflakeIdWorker worker = new SnowflakeIdWorker(0, 0);
        return String.valueOf(worker.nextId());
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

        BigDecimal total = new BigDecimal("0");
        BigDecimal promotion = new BigDecimal("0");
        BigDecimal freight = new BigDecimal("0");
        BigDecimal coupon = new BigDecimal("0");
        if (!CollectionUtils.isEmpty(cartItems)) {
            for (OmsCartItem cartItem : cartItems) {
                total = total.add(cartItem.getPrice().multiply(new BigDecimal(cartItem.getQuantity())));
            }
        }
        return ConfirmOrderResult.CalcAmount.builder()
                .total(total)
                .promotion(promotion)
                .freight(freight)
                .coupon(coupon)
                .build();
    }
}
