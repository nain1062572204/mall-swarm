package com.wang.mall.admin.dao;

import com.wang.mall.admin.dto.PmsProductCategoryWithChildrenItem;

import java.util.List;

/**
 * @author 王念
 * @create 2020-02-10 17:14
 */
public interface PmsProductCategoryDao {
    List<PmsProductCategoryWithChildrenItem> listWithChildren();
}
