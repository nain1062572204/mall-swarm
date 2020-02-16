package com.wang.mall.domain;

import com.wang.mall.model.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

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
    //分类信息
    private List<Map<String, List<PmsProductCategory>>> categories;
    //推荐分类信息
    private List<Map<String, List<PmsProduct>>> recommendCategories;
}
