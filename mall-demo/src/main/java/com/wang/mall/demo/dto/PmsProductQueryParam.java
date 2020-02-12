package com.wang.mall.demo.dto;

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
    @ApiModelProperty("上架状态")
    private Integer publishStatus;
    @ApiModelProperty("审核状态")
    private Integer verifyStatus;
    @ApiModelProperty("商品名称模糊关键字")
    private String keyword;
    @ApiModelProperty("商品货号")
    private String productSn;
    @ApiModelProperty("商品分类编号")
    private Long productCategoryId;
}
