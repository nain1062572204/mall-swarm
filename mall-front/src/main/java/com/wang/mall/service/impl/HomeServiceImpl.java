package com.wang.mall.service.impl;

import com.wang.mall.domain.HomeContentResult;
import com.wang.mall.model.SmsHomeAdvertise;
import com.wang.mall.service.HomeService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 王念
 * @create 2020-02-04 18:50
 */
@Service
public class HomeServiceImpl implements HomeService {
    @Override
    public HomeContentResult content() {
        HomeContentResult contentResult = new HomeContentResult();
        List<SmsHomeAdvertise> advertises = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            SmsHomeAdvertise homeAdvertise = new SmsHomeAdvertise();
            homeAdvertise.setId(new Long(i));
            homeAdvertise.setPic("xxxxxxxxxxxxxxxxx");
            advertises.add(homeAdvertise);
        }
        contentResult.setAdvertises(advertises);
        return contentResult;
    }
}
