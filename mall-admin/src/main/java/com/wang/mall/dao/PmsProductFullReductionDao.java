package com.wang.mall.dao;

import com.wang.mall.model.PmsProductFullReduction;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 自定义商品满减价格dao
 *
 * @author 王念
 * @create 2020-02-11 17:27
 */
public interface PmsProductFullReductionDao {
    int insertList(@Param("list") List<PmsProductFullReduction> productFullReductions);
}
