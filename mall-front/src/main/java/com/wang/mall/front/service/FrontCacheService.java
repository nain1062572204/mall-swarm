package com.wang.mall.front.service;

import com.wang.mall.front.dto.PmsProductCategoryWithProduct;
import com.wang.mall.front.dto.SmsFlashPromotionWithProduct;
import com.wang.mall.model.OmsCartItem;
import com.wang.mall.model.PmsProductCategory;
import com.wang.mall.model.SmsHomeAdvertise;
import com.wang.mall.model.UmsMember;

import java.lang.reflect.Member;
import java.util.List;
import java.util.Map;

/**
 * 缓存service
 *
 * @author 王念
 * @create 2020-03-24 18:41
 */
public interface FrontCacheService {

    //获取用户购物车信息
    List<OmsCartItem> getCartItem(Long memberId);

    //删除用户购物车信息
    void delCartItem(Long memberId);

    //获取首页导航条分类信息
    List<PmsProductCategory> getNavCategoryList();

    //删除首页导航条分类信息
    void delNavCategoryList();

    //获取首页分类及商品
    List<PmsProductCategoryWithProduct> getHomeCategories();

    //删除首页分类及商品信息
    void delHomeCategories();

    //获取推荐商品信息
    List<PmsProductCategoryWithProduct> getRecommendProduct();

    //删除推荐商品
    void delRecommendProduct();

    //获取首页广告信息
    Map<String, List<SmsHomeAdvertise>> getAdvertise();

    //删除广告信息
    void delAdvertise();

    //获取用户信息
    UmsMember getMember(String username);

    //删除用户
    void delMember(String username);

    //设置用户
    void setMember(UmsMember member);
}
