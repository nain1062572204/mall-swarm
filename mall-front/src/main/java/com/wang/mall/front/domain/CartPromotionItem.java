package com.wang.mall.front.domain;

import com.wang.mall.model.OmsOrderItem;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * 购物车中促销信息封装
 *
 * @author 王念
 * @create 2020-03-22 16:53
 */
@Getter
@Setter
public class CartPromotionItem extends OmsOrderItem {
    private static final long serialVersionUID = 3142673896679501117L;

    //促销活动信息
    private String promotionMessage;
    //促销活动减去的金额，针对每个商品
    private BigDecimal reduceAmount;
    //商品的真实库存（剩余库存-锁定库存）
    private Integer realStock;
    //购买商品赠送积分
    private Integer integration;
    //购买商品赠送成长值
    private Integer growth;
}
