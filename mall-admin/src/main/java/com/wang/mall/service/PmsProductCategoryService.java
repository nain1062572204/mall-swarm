package com.wang.mall.service;

import com.wang.mall.dto.PmsProductCategoryParam;
import com.wang.mall.dto.PmsProductCategoryWithChildrenItem;
import com.wang.mall.model.PmsProductCategory;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 王念
 * @create 2020-02-07 16:50
 * 产品分类Service
 */
public interface PmsProductCategoryService {
    @Transactional
    int create(PmsProductCategoryParam productCategoryParam);

    @Transactional
    int update(Long id, PmsProductCategoryParam productCategoryParam);

    List<PmsProductCategory> list(Long parentId, Integer pageSize, Integer pageNum);

    int delete(Long id);

    PmsProductCategory item(Long id);

    int updateNavStatus(List<Long> ids, Integer navStatus);

    int updateShowStatus(List<Long> ids, Integer showStatus);

    List<PmsProductCategoryWithChildrenItem> listWithChildren();
}
