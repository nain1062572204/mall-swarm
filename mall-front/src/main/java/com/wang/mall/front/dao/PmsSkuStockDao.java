package com.wang.mall.front.dao;

import com.wang.mall.front.domain.OrderParam;
import com.wang.mall.model.PmsSkuStock;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品库存自定义dao
 *
 * @author 王念
 * @create 2020-03-30 22:14
 */
public interface PmsSkuStockDao {
    //获取数据的同时给该行加排它锁
    List<PmsSkuStock> getPmsSkuListForUpdate(@Param("ids") List<Long> ids);

    //更新商品库存
    Integer updatePmsSkuStock(@Param("productInfos") List<OrderParam.ProductInfo> productInfos);
}
