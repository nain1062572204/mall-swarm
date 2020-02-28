package com.wang.mall.front.service;

import com.wang.mall.front.domain.PmsProductDetailResult;

/**
 * 商品Service
 *
 * @author 王念
 * @create 2020-02-28 17:42
 */
public interface PmsProductService {
    //根据商品id获取商品库存、参数信息
    PmsProductDetailResult getProductDetail(Long id);
}
