package com.wang.mall.admin.dto;

import com.wang.mall.model.PmsProductAttribute;
import com.wang.mall.model.PmsProductAttributeCategory;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 包含分类下属性的dto
 *
 * @author 王念
 * @create 2020-02-14 22:49
 */
public class PmsProductAttributeCategoryItem extends PmsProductAttributeCategory {
    private static final long serialVersionUID = 1475842263508292190L;
    @Getter
    @Setter
    private List<PmsProductAttribute> productAttributes;

}
