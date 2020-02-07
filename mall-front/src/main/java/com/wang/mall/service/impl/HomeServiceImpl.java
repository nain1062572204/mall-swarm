package com.wang.mall.service.impl;

import com.wang.mall.domain.HomeContentResult;
import com.wang.mall.mapper.PmsProductCategoryMapper;
import com.wang.mall.mapper.SmsHomeAdvertiseMapper;
import com.wang.mall.mapper.SmsHomeBannerMapper;
import com.wang.mall.mapper.SmsHomePromoMapper;
import com.wang.mall.model.PmsProductCategory;
import com.wang.mall.model.PmsProductCategoryExample;
import com.wang.mall.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
    private PmsProductCategoryMapper productCategoryMapper;

    @Override
    public HomeContentResult content() {
        HomeContentResult contentResult = new HomeContentResult();
        //获取分类信息
        PmsProductCategoryExample categoryExample = new PmsProductCategoryExample();
        categoryExample.createCriteria()
                .andNavStatusEqualTo(1)
                .andShowStatusEqualTo(1);
        List<PmsProductCategory> productCategories = productCategoryMapper.selectByExample(categoryExample);
        Map<String, List<PmsProductCategory>> map = new HashMap<>();
        map.put("手机/数码", productCategories);
        List<Map<String, List<PmsProductCategory>>> result = new ArrayList<>();
        result.add(map);
        contentResult.setCategories(result);
        return contentResult;
    }
}
