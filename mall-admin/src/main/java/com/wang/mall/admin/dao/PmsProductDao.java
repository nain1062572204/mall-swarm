package com.wang.mall.admin.dao;

import com.wang.mall.admin.dto.PmsProductResult;
import org.apache.ibatis.annotations.Param;

/**
 * 自定义商品dao
 *
 * @author 王念
 * @create 2020-02-11 17:32
 */
public interface PmsProductDao {
    /**
     * 获取商品编辑信息
     */
    PmsProductResult getUpdateInfo(@Param("id") Long id);
}
