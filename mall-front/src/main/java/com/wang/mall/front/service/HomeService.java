package com.wang.mall.front.service;

import com.wang.mall.front.domain.HomeContentResult;
import com.wang.mall.front.domain.TopBarContentResult;


/**
 * @author 王念
 * @create 2020-02-04 18:48
 */
public interface HomeService {
    /**
     * 获取首页内容
     */
    HomeContentResult content();

    /**
     * 获取TopBar内容
     */
    TopBarContentResult topBarContent();
}
