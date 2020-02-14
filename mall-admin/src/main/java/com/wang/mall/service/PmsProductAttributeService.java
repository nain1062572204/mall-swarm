package com.wang.mall.service;

import com.wang.mall.dto.PmsProductAttributeParam;
import com.wang.mall.dto.ProductAttrInfo;
import com.wang.mall.model.PmsProductAttribute;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 商品属性Service
 *
 * @author 王念
 * @create 2020-02-14 20:31
 */
public interface PmsProductAttributeService {
    /**
     * 根据分类获取商品属性
     *
     * @param cid  分类id
     * @param type 0->属性；2->参数
     */
    List<PmsProductAttribute> list(Long cid, Integer type, Integer pageSize, Integer pageNum);

    /**
     * 添加商品属性
     */
    @Transactional
    int create(PmsProductAttributeParam productAttributeParam);

    /**
     * 修改商品属性
     */
    int update(Long id, PmsProductAttributeParam productAttributeParam);

    /**
     * 获取单个商品属性
     */
    PmsProductAttribute item(Long id);

    /**
     * 删除属性
     */
    @Transactional
    int delete(List<Long> ids);

    List<ProductAttrInfo> productAttrInfo(Long productCategoryId);
}
