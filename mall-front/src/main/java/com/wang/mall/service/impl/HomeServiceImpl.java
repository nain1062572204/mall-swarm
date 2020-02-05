package com.wang.mall.service.impl;

import com.wang.mall.service.HomeService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 王念
 * @create 2020-02-04 18:50
 */
@Service
public class HomeServiceImpl implements HomeService {
    @Override
    public Map<String, Object> getPageInfo() {
        //获取分类信息
        Map<String, Object> result = new HashMap<>(16);
        //一级分类

        result.put("手机 通讯", new String[]{"手机", "OnePlus 7Pro", "华为 P30", "华为 C8813D"});
        return result;
    }
}
