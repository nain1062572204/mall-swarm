package com.wang.mall.front.domain;

import com.wang.mall.model.OmsCartItem;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

/**
 * 确认订单信息封装
 *
 * @author 王念
 * @create 2020-03-22 16:52
 */
@Getter
@Setter
@Builder
public class ConfirmOrderResult {
    //包含优惠信息的购物车信息
    private List<OmsCartItem> cartItemList;
    //计算的金额
    private CalcAmount calcAmount;

    @Getter
    @Setter
    @Builder
    public static class CalcAmount {
        //总价
        private BigDecimal total;
        //活动优惠
        private BigDecimal promotion;
        //优惠券
        private BigDecimal coupon;
        //运费
        private BigDecimal freight;
    }
}
