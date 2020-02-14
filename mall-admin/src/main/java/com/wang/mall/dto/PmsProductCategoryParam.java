package com.wang.mall.dto;

import com.wang.mall.validator.FlagValidator;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @author 王念
 * @create 2020-02-10 17:18
 * 添加商品分类的参数
 */
@Getter
@Setter
public class PmsProductCategoryParam {
    @ApiModelProperty(value = "父分类id",example = "0")
    private Long parentId;
    @ApiModelProperty(value = "商品分类名称", required = true)
    @NotEmpty(message = "商品分类名不能为空")
    private String name;
    @ApiModelProperty("分类单位")
    private String productUnit;
    @ApiModelProperty(value = "是否在导航栏显示",example = "1")
    @FlagValidator(value = {"0", "1"}, message = "状态只能为0或1")
    private Integer navStatus;
    @ApiModelProperty(value = "是否进行显示",example = "1")
    @FlagValidator(value = {"0", "1"}, message = "状态只能为0或1")
    private Integer showStatus;
    @ApiModelProperty(value = "排序",example = "0")
    @Min(value = 0, message = "排序最小为0")
    private Integer sort;
    @ApiModelProperty("图标")
    private String icon;
    @ApiModelProperty("关键字")
    private String keywords;
    @ApiModelProperty("描述")
    private String description;
    @ApiModelProperty("产品相关筛选属性集合")
    private List<Long> productAttributeIdList;
}
