package com.wang.mall.admin.service.impl;

import com.wang.mall.admin.service.AdminCacheService;
import com.wang.mall.cache.keys.RedisKeys;
import com.wang.mall.cache.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 王念
 * @create 2020-04-12 20:54
 */
@Service
public class AdminCacheServiceImpl implements AdminCacheService {
    @Autowired
    private RedisService redisService;

    @Override
    public void delProduct(Long productId) {
        redisService.del(RedisKeys.PMS_PRODUCT.getKey() + ":" + productId);
    }
}
