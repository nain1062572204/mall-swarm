package com.wang.mall.front.service.impl;

import com.wang.mall.cache.keys.RedisKeys;
import com.wang.mall.cache.service.RedisService;
import com.wang.mall.front.domain.PmsProductDetailResult;
import com.wang.mall.front.dto.PmsProductCategoryWithProduct;
import com.wang.mall.front.service.FrontCacheService;
import com.wang.mall.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author 王念
 * @create 2020-03-24 18:54
 */
@Service
@SuppressWarnings("unchecked")
public class FrontCacheServiceImpl implements FrontCacheService {
    @Autowired
    private RedisService redisService;

    @Override
    public List<OmsCartItem> getCartItem(Long memberId) {
        return (List<OmsCartItem>) redisService.get(RedisKeys.OMS_CART_ITEM.getKey() + memberId);
    }

    @Override
    public void delCartItem(Long memberId) {
        redisService.del(RedisKeys.OMS_CART_ITEM.getKey() + memberId);
    }

    @Override
    public List<PmsProductCategory> getNavCategoryList() {
        return (List<PmsProductCategory>) redisService.get(RedisKeys.NAV_CATEGORY.getKey());
    }

    @Override
    public void delNavCategoryList() {
        redisService.del(RedisKeys.NAV_CATEGORY.getKey());
    }

    @Override
    public List<PmsProductCategoryWithProduct> getHomeCategories() {
        return (List<PmsProductCategoryWithProduct>) redisService.get(RedisKeys.CATEGORY.getKey());
    }

    @Override
    public void delHomeCategories() {
        redisService.del(RedisKeys.CATEGORY.getKey());
    }

    @Override
    public List<PmsProductCategoryWithProduct> getRecommendProduct() {
        return (List<PmsProductCategoryWithProduct>) redisService.get(RedisKeys.HOME_PRODUCT.getKey());
    }

    @Override
    public void delRecommendProduct() {
        redisService.del(RedisKeys.HOME_PRODUCT.getKey());
    }

    @Override
    public Map<String, List<SmsHomeAdvertise>> getAdvertise() {
        return (Map<String, List<SmsHomeAdvertise>>) redisService.get(RedisKeys.HOME_ADVERTISE.getKey());
    }

    @Override
    public void delAdvertise() {
        redisService.del(RedisKeys.HOME_ADVERTISE.getKey());
    }

    @Override
    public UmsMember getMember(String username) {
        return (UmsMember) redisService.get(RedisKeys.MEMBER.getKey() + ":" + username);
    }

    @Override
    public void delMember(String username) {
        redisService.del(RedisKeys.MEMBER.getKey() + ":" + username);
    }

    @Override
    public void setMember(UmsMember member) {
        redisService.set(RedisKeys.MEMBER + ":" + member.getUsername(), member, 60 * 60 * 24);
    }

    @Override
    public void setProduct(PmsProductDetailResult productDetailResult) {
        redisService.set(RedisKeys.PMS_PRODUCT.getKey() + ":" + productDetailResult.getId(), productDetailResult, 60 * 60 * 24);
    }

    @Override
    public PmsProductDetailResult getProduct(Long productId) {
        return (PmsProductDetailResult) redisService.get(RedisKeys.PMS_PRODUCT.getKey() + ":" + productId);
    }

    @Override
    public void delProduct(Set<Long> ids) {
        List<String> keys = new ArrayList<>(ids.size());
        for (Long id : ids) {
            keys.add(RedisKeys.PMS_PRODUCT.getKey() + ":" + id);
        }
        redisService.del(keys);
    }

    @Override
    public List<SmsHomeAdvertise> getSearchAdvertise() {
        return (List<SmsHomeAdvertise>) redisService.get(RedisKeys.SEARCH_ADVERTISE.getKey());
    }

    @Override
    public void delSearchAdvertise() {

    }

    @Override
    public void setSearchAdvertise(List<SmsHomeAdvertise> advertise) {
        redisService.set(RedisKeys.SEARCH_ADVERTISE.getKey(), advertise, 60 * 60 * 24);
    }
}
