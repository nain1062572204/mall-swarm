package com.wang.mall.admin.service;

import com.wang.mall.model.PmsSkuStock;

import java.util.List;

/**
 * sku商品库存管理service
 *
 * @author 王念
 * @create 2020-02-15 20:29
 */
public interface PmsSkuStockService {
    /**
     * 根据id和skuCode模糊搜索
     *
     * @param pid 产品id
     */
    List<PmsSkuStock> list(Long pid, String keyword);

    /**
     * 批量更新商品库存信息
     */
    int update(Long pid, List<PmsSkuStock> skuStocks);
}
