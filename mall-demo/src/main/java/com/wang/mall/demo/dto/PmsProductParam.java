package com.wang.mall.demo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


/**
 * 创建或修改商品时需要的参数
 *
 * @author 王念
 * @create 2020-02-11 16:45
 */
@Getter
@Setter
public class PmsProductParam extends PmsProduct {
    private static final long serialVersionUID = -342532281459917481L;
    @ApiModelProperty("商品阶梯价格设置")
    private List<PmsProductLadder> productLadders;
    @ApiModelProperty("商品满减价格设置")
    private List<PmsProductFullReduction> productFullReductions;
    @ApiModelProperty("商品sku库存信息")
    private List<PmsSkuStock> skuStocks;
    @ApiModelProperty("商品参数和自定义属性")
    private List<PmsProductAttributeValue> productAttributeValues;
}