package com.wang.mall.admin.dto;

import com.wang.mall.admin.validator.FlagValidator;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

/**
 * 商品属性参数
 *
 * @author 王念
 * @create 2020-02-14 20:34
 */
@Getter
@Setter
public class PmsProductAttributeParam {
    @ApiModelProperty(value = "属性分类ID", example = "1")
    @NotEmpty(message = "属性分类不能为空")
    private Long productAttributeCategoryId;
    @ApiModelProperty("属性名称")
    @NotEmpty(message = "属性名称不能为空")
    private String name;
    @ApiModelProperty(value = "属性选择类型：0->唯一；1->单选；2->多选", example = "0")
    @FlagValidator({"0", "1", "2"})
    private Integer selectType;
    @ApiModelProperty(value = "属性录入方式：0->手工录入；1->从列表中选取", example = "0")
    @FlagValidator({"0", "1"})
    private Integer inputType;
    @ApiModelProperty("可选值列表，以逗号隔开")
    private String inputList;

    private Integer sort;
    @ApiModelProperty(value = "分类筛选样式：0->普通；1->颜色", example = "0")
    @FlagValidator({"0", "1"})
    private Integer filterType;
    @ApiModelProperty(value = "检索类型；0->不需要进行检索；1->关键字检索；2->范围检索", example = "0")
    @FlagValidator({"0", "1", "2"})
    private Integer searchType;
    @ApiModelProperty("相同属性产品是否关联；0->不关联；1->关联")
    @FlagValidator({"0", "1"})
    private Integer relatedStatus;
    @ApiModelProperty(value = "是否支持手动新增；0->不支持；1->支持", example = "1")
    @FlagValidator({"0", "1"})
    private Integer handAddStatus;
    @ApiModelProperty(value = "属性的类型；0->规格；1->参数", example = "0")
    @FlagValidator({"0", "1"})
    private Integer type;
}
