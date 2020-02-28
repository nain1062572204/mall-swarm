package com.wang.mall.front.service.impl;

import com.wang.mall.front.dao.PmsProductDao;
import com.wang.mall.front.domain.PmsProductDetailResult;
import com.wang.mall.front.service.PmsProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 王念
 * @create 2020-02-28 18:03
 */
@Service
@Slf4j
public class PmsProductServiceImpl implements PmsProductService {
    @Autowired
    private PmsProductDao productDao;

    @Override
    public PmsProductDetailResult getProductDetail(Long id) {
        return productDao.getProductDetail(id);
    }
}
