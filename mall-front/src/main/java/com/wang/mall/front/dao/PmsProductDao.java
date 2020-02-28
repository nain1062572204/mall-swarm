package com.wang.mall.front.dao;

import com.wang.mall.front.domain.PmsProductDetailResult;
import org.apache.ibatis.annotations.Param;

/**
 * 商品自定义dao
 *
 * @author 王念
 * @create 2020-02-28 17:49
 */
public interface PmsProductDao {
    PmsProductDetailResult getProductDetail(@Param("id") Long id);
}
