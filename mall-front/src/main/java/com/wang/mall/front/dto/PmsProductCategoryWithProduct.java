package com.wang.mall.front.dto;

import com.wang.mall.model.PmsProduct;
import com.wang.mall.model.PmsProductCategory;
import lombok.Data;

import java.util.List;

/**
 * @author 王念
 * @create 2020-02-19 19:49
 */
@Data
public class PmsProductCategoryWithProduct extends PmsProductCategory {
    private static final long serialVersionUID = -515261764478995600L;
    //分类下的商品
    List<PmsProduct> pmsProducts;
}
