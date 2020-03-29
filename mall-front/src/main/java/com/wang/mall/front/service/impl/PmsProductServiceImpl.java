package com.wang.mall.front.service.impl;

import com.wang.mall.front.dao.PmsProductDao;
import com.wang.mall.front.domain.PmsProductDetailResult;
import com.wang.mall.front.service.FrontCacheService;
import com.wang.mall.front.service.PmsProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author 王念
 * @create 2020-02-28 18:03
 */
@Service
@Slf4j
public class PmsProductServiceImpl implements PmsProductService {
    @Autowired
    private PmsProductDao productDao;
    @Autowired
    private FrontCacheService frontCacheService;

    @Override
    public PmsProductDetailResult getProductDetail(Long id) {
        //先从缓存中获取
        PmsProductDetailResult result = frontCacheService.getProduct(id);
        if (!Objects.isNull(result))
            return result;
        result = productDao.getProductDetail(id);
        //非空放入缓存
        if (!Objects.isNull(result))
            frontCacheService.setProduct(result);
        return result;
    }
}
