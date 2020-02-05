package com.wang.mall.domain;

import com.wang.mall.model.SmsHomeAdvertise;
import com.wang.mall.model.SmsHomeBanner;
import com.wang.mall.model.SmsHomePromo;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * @author 王念
 * @create 2020-02-04 21:37
 * 首页内容返回信息封装
 */
@Getter
@Setter
public class HomeContentResult implements Serializable {
    private static final long serialVersionUID = -2903766900882022590L;
    //首页轮播图
    private List<SmsHomeAdvertise> advertises;
    //轮播图下方广告
    private List<SmsHomePromo> promos;
    //中间横线广告
    private List<SmsHomeBanner> banners;


}
