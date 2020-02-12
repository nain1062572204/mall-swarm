package com.wang.mall.dao;

import com.wang.mall.model.PmsProductLadder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 自定义商品阶梯价格dao
 *
 * @author 王念
 * @create 2020-02-11 17:25
 */
public interface PmsProductLadderDao {
    int insertList(@Param("list") List<PmsProductLadder> productLadders);
}
