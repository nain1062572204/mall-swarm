package com.wang.mall.admin.service;

import com.wang.mall.admin.dto.PmsProductAttributeCategoryItem;
import com.wang.mall.model.PmsProductAttributeCategory;

import java.util.List;

/**
 * 商品属性分类Sevice
 *
 * @author 王念
 * @create 2020-02-14 22:47
 */
public interface PmsProductAttributeCategoryService {
    int create(String name);

    int update(Long id, String name);

    int delete(Long id);

    PmsProductAttributeCategory item(Long id);

    List<PmsProductAttributeCategory> list(Integer pageSize, Integer pageNum);

    List<PmsProductAttributeCategoryItem> listWithAttr();

}
