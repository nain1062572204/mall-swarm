package com.wang.mall.dto;

import com.wang.mall.model.PmsProductCategory;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author 王念
 * @create 2020-02-10 17:09
 */
@Getter
@Setter
public class PmsProductCategoryWithChildrenItem extends PmsProductCategory {
    //子分类
    private List<PmsProductCategory> children;
}
