package com.wang.mall.service.impl;

import com.wang.mall.domain.HomeContentResult;
import com.wang.mall.mapper.SmsHomeAdvertiseMapper;
import com.wang.mall.mapper.SmsHomeBannerMapper;
import com.wang.mall.mapper.SmsHomePromoMapper;
import com.wang.mall.model.PmsProductCategory;
import com.wang.mall.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author 王念
 * @create 2020-02-04 18:50
 */
@Service
public class HomeServiceImpl implements HomeService {
    @Autowired
    private SmsHomeAdvertiseMapper advertiseMapper;
    @Autowired
    private SmsHomeBannerMapper bannerMapper;
    @Autowired
    private SmsHomePromoMapper promoMapper;
    @Autowired
    private PmsProductCategory pmsProductCategory;

    @Override
    public HomeContentResult content() {
        HomeContentResult contentResult = new HomeContentResult();
        return contentResult;
    }
}
