package com.wang.mall.front.service.impl;

import com.wang.mall.front.domain.CartPromotionItem;
import com.wang.mall.front.domain.ConfirmOrderResult;
import com.wang.mall.front.service.OmsCartItemService;
import com.wang.mall.front.service.OmsOrderService;
import com.wang.mall.front.service.UmsMemberReceiveAddressService;
import com.wang.mall.front.service.UmsMemberService;
import com.wang.mall.model.OmsCartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.List;

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
    private UmsMemberReceiveAddressService addressService;

    @Override
    public ConfirmOrderResult generateConfirmOrder(List<Long> ids) {
        //获取订单包含的商品
        List<OmsCartItem> cartItemList = cartItemService.list(ids);
        return ConfirmOrderResult.builder().cartItemList(cartItemList).calcAmount(total(cartItemList)).build();
    }

    //计算总价
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
