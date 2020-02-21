package com.wang.mall.front.dto;

import com.wang.mall.model.PmsProductCategory;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author 王念
 * @create 2020-02-19 22:26
 */
@Getter
@Setter
public class PmsProductCategoryWithChildrenItem extends PmsProductCategory {
    private static final long serialVersionUID = 5837771808349633580L;
    List<PmsProductCategory> children;
}
