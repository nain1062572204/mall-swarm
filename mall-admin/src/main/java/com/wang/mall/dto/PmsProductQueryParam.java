package com.wang.mall.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author 王念
 * @create 2020-02-11 17:19
 */
@Getter
@Setter
public class PmsProductQueryParam {
    @ApiModelProperty(value = "上架状态", example = "1")
    private Integer publishStatus;
    @ApiModelProperty(value = "审核状态", example = "1")
    private Integer verifyStatus;
    @ApiModelProperty(value = "商品名称模糊关键字")
    private String keyword;
    @ApiModelProperty("商品货号")
    private String productSn;
    @ApiModelProperty(value = "商品分类编号", example = "1")
    private Long productCategoryId;
}
