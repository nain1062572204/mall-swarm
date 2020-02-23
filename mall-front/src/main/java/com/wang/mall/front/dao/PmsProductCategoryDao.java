package com.wang.mall.front.dao;

import com.wang.mall.front.dto.PmsProductCategoryWithChildrenItem;

import java.util.List;

/**
 * 商品分类自定义dao
 *
 * @author 王念
 * @create 2020-02-22 17:31
 */
public interface PmsProductCategoryDao {
    List<PmsProductCategoryWithChildrenItem> listShowStatusWithChildren();
}
