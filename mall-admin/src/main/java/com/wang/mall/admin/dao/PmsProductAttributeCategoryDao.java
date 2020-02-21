package com.wang.mall.admin.dao;

import com.wang.mall.admin.dto.PmsProductAttributeCategoryItem;

import java.util.List;

/**
 * 商品分类属性自定义dao
 *
 * @author 王念
 * @create 2020-02-14 22:52
 */
public interface PmsProductAttributeCategoryDao {
    List<PmsProductAttributeCategoryItem> listWithAttr();
}
