package com.wang.mall.dto;

import com.wang.mall.model.PmsProduct;
import com.wang.mall.model.SmsFlashPromotionProductRelation;
import lombok.Getter;
import lombok.Setter;

/**
 * 闪购商品信息封装
 *
 * @author 王念
 * @create 2020-02-14 15:08
 */
public class SmsFlashPromotionProduct extends SmsFlashPromotionProductRelation {
    private static final long serialVersionUID = -7019630722228532967L;

    @Getter
    @Setter
    private PmsProduct product;
}
