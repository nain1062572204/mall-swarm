package com.wang.mall.dto;

import com.wang.mall.model.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;


/**
 * 创建或修改商品时需要的参数
 *
 * @author 王念
 * @create 2020-02-11 16:45
 */
@Getter
@Setter
@ToString
public class PmsProductParam extends PmsProduct {
    private static final long serialVersionUID = -342532281459917481L;
    @ApiModelProperty("商品阶梯价格设置")
    private List<PmsProductLadder> productLadderList;
    @ApiModelProperty("商品满减价格设置")
    private List<PmsProductFullReduction> productFullReductionList;
    @ApiModelProperty("商品的sku库存信息")
    private List<PmsSkuStock> skuStockList;
    @ApiModelProperty("商品参数及自定义规格属性")
    private List<PmsProductAttributeValue> productAttributeValueList;

}
