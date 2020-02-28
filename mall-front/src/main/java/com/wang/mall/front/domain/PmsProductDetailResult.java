package com.wang.mall.front.domain;

import com.wang.mall.model.PmsProduct;
import com.wang.mall.model.PmsSkuStock;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * 商品详情信息返回封装
 *
 * @author 王念
 * @create 2020-02-28 17:44
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PmsProductDetailResult extends PmsProduct {
    private static final long serialVersionUID = -3338557821210807086L;

    private List<PmsSkuStock> skuStocks;
}
