package com.wang.mall.front.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wang.mall.front.dao.SmsFlashPromotionProductRelationDao;
import com.wang.mall.front.domain.HomeContentResult;
import com.wang.mall.front.dto.PmsProductCategoryWithChildrenItem;
import com.wang.mall.front.dto.PmsProductCategoryWithProduct;
import com.wang.mall.front.dto.SmsFlashPromotionWithProduct;
import com.wang.mall.mapper.PmsProductCategoryMapper;
import com.wang.mall.mapper.PmsProductMapper;
import com.wang.mall.mapper.SmsFlashPromotionSessionMapper;
import com.wang.mall.mapper.SmsHomeAdvertiseMapper;
import com.wang.mall.model.*;
import com.wang.mall.front.service.HomeService;
import com.wang.mall.front.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.Collectors;


/**
 * @author 王念
 * @create 2020-02-04 18:50
 */
@Service
@Slf4j
public class HomeServiceImpl implements HomeService {

    @Value("${spring.redis.key.category}")
    private String REDIS_KEY_CATEGORY;
    @Value("${spring.redis.key.homeAdvertise}")
    private String REDIS_KEY_HOME_ADVERTISE;
    @Value("${spring.redis.key.flashPromotion}")
    private String REDIS_KEY_FLASH_PROMOTION;
    @Value("${spring.redis.key.homeProduct}")
    private String REDIS_KEY_HOME_PRODUCT;
    @Value("${spring.redis.expireTime}")
    private Integer expireTime;

    @Autowired
    private ThreadPoolTaskExecutor executor;
    @Autowired
    private RedisService redisService;
    @Autowired
    private SmsHomeAdvertiseMapper advertiseMapper;
    @Autowired
    private PmsProductCategoryMapper productCategoryMapper;
    @Autowired
    private PmsProductMapper productMapper;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private SmsFlashPromotionSessionMapper flashPromotionSessionMapper;
    @Autowired
    private SmsFlashPromotionProductRelationDao flashPromotionProductRelationDao;

    @Override
    public HomeContentResult content() {
        //广告信息
        Map<String, List<SmsHomeAdvertise>> advertises = null;
        //分类信息
        List<PmsProductCategoryWithChildrenItem> categories = null;
        //推荐商品
        List<PmsProductCategoryWithProduct> recommendProducts = null;
        //秒杀
        SmsFlashPromotionWithProduct flash = null;

        Future<Map<String, List<SmsHomeAdvertise>>> advertiseFuture = executor.submit(this::getAdvertise);
        Future<List<PmsProductCategoryWithProduct>> recommendProductFuture = executor.submit(this::getRecommendProduct);
        Future<List<PmsProductCategoryWithChildrenItem>> categoriesFuture = executor.submit(this::getRecommendCategories);
        Future<SmsFlashPromotionWithProduct> flashFuture = executor.submit(this::getFlash);
        try {
            advertises = advertiseFuture.get();
            recommendProducts = recommendProductFuture.get();
            categories = categoriesFuture.get();
            flash = flashFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return HomeContentResult.builder()
                .advertises(advertises)
                .categories(categories)
                .recommendProducts(recommendProducts)
                .flashPromotion(flash)
                .build();
    }

    /**
     * 获取广告
     */
    private Map<String, List<SmsHomeAdvertise>> getAdvertise() {
        final String CAROUSEL = "carousel";
        final String PROMO = "promo";
        final String BANNER = "banner";
        Map<String, List<SmsHomeAdvertise>> result = null;
        String advertiseJson = redisService.get(REDIS_KEY_HOME_ADVERTISE);
        if (StringUtils.isEmpty(advertiseJson)) {
            //redis内容为空，从mysql中获取
            SmsHomeAdvertiseExample example = new SmsHomeAdvertiseExample();
            Date currentDate = new Date();
            example.createCriteria().andStatusEqualTo(1)
                    .andStartTimeLessThan(currentDate)
                    .andEndTimeGreaterThan(currentDate);
            List<SmsHomeAdvertise> advertises = advertiseMapper.selectByExample(example);
            result = new HashMap<>(4);
            result.put(CAROUSEL, advertises.stream().filter(advertise -> advertise.getType() == 0).collect(Collectors.toList()));
            result.put(PROMO, advertises.stream().filter(advertise -> advertise.getType() == 1).collect(Collectors.toList()));
            result.put(BANNER, advertises.stream().filter(advertise -> advertise.getType() == 2).collect(Collectors.toList()));
            try {
                redisService.set(REDIS_KEY_HOME_ADVERTISE, objectMapper.writeValueAsString(result));
                redisService.expire(REDIS_KEY_HOME_ADVERTISE, expireTime);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return result;
        } else {
            try {
                result = objectMapper.readValue(advertiseJson, new TypeReference<Map<String, List<SmsHomeAdvertise>>>() {
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 获取推荐商品
     */
    private List<PmsProductCategoryWithProduct> getRecommendProduct() {
        List<PmsProductCategoryWithProduct> result = null;
        String productJson = redisService.get(REDIS_KEY_HOME_PRODUCT);
        if (StringUtils.isEmpty(productJson)) {
            //获取推荐分类
            PmsProductCategoryExample example = new PmsProductCategoryExample();
            example.createCriteria().andShowStatusEqualTo(1)
                    .andRecommandStatusEqualTo(1);
            List<PmsProductCategory> recommendCategoryList = productCategoryMapper.selectByExample(example);
            //获取推荐商品
            PmsProductExample productExample = new PmsProductExample();
            productExample.createCriteria()
                    .andPublishStatusEqualTo(1)
                    .andDeleteStatusEqualTo(0)
                    .andVerifyStatusEqualTo(1)
                    .andRecommandStatusEqualTo(1);
            List<PmsProduct> recommendProductList = productMapper.selectByExample(productExample);
            result = new ArrayList<>();
            if (!CollectionUtils.isEmpty(recommendProductList)) {
                for (PmsProductCategory productCategory : recommendCategoryList) {
                    PmsProductCategoryWithProduct item = new PmsProductCategoryWithProduct();
                    BeanUtils.copyProperties(productCategory, item);
                    //将商品分类id为推荐分类id的商品与分类绑定
                    item.setPmsProducts(
                            recommendProductList.stream()
                                    .filter(product -> product.getProductCategoryId().equals(productCategory.getId()))
                                    .collect(Collectors.toList())
                    );
                    result.add(item);
                }
            }
            try {
                //放入redis
                redisService.set(REDIS_KEY_HOME_PRODUCT, objectMapper.writeValueAsString(result));
                redisService.expire(REDIS_KEY_HOME_PRODUCT, expireTime);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        } else {

            try {
                result = objectMapper.readValue(productJson, new TypeReference<List<PmsProductCategoryWithProduct>>() {
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 获取首页分类
     */
    private List<PmsProductCategoryWithChildrenItem> getRecommendCategories() {
        List<PmsProductCategoryWithChildrenItem> result = null;
        String categoryJson = redisService.get(REDIS_KEY_CATEGORY);
        if (StringUtils.isEmpty(categoryJson)) {
            PmsProductCategoryExample parentExample = new PmsProductCategoryExample();
            parentExample.createCriteria()
                    .andParentIdEqualTo(0L)
                    .andShowStatusEqualTo(1);
            PmsProductCategoryExample showExample = new PmsProductCategoryExample();
            showExample.createCriteria()
                    .andParentIdNotEqualTo(0L)
                    .andShowStatusEqualTo(1);
            List<PmsProductCategory> parentList = productCategoryMapper.selectByExample(parentExample);
            List<PmsProductCategory> showList = productCategoryMapper.selectByExample(showExample);
            result = new ArrayList<>();
            for (PmsProductCategory productCategory : parentList) {
                PmsProductCategoryWithChildrenItem item = new PmsProductCategoryWithChildrenItem();
                BeanUtils.copyProperties(productCategory, item);
                item.setChildren(showList.stream().filter(c -> c.getParentId().equals(productCategory.getId())).collect(Collectors.toList()));
                result.add(item);
            }
            try {
                redisService.set(REDIS_KEY_CATEGORY, objectMapper.writeValueAsString(result));
                redisService.expire(REDIS_KEY_CATEGORY, expireTime);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return result;
        } else {
            try {
                result = objectMapper.readValue(categoryJson, new TypeReference<List<PmsProductCategoryWithChildrenItem>>() {
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result;
        }

    }

    /**
     * 获取当前时段开启的闪购
     */
    private SmsFlashPromotionWithProduct getFlash() {
        SmsFlashPromotionSessionExample example = new SmsFlashPromotionSessionExample();
        Date currentTime = new Date();
        example.createCriteria()
                .andStatusEqualTo(1)
                .andStartTimeLessThan(currentTime)
                .andEndTimeGreaterThan(currentTime);
        List<SmsFlashPromotionSession> list = flashPromotionSessionMapper.selectByExample(example);
        SmsFlashPromotionWithProduct flashPromotionWithProduct = new SmsFlashPromotionWithProduct();
        if (!CollectionUtils.isEmpty(list)) {
            SmsFlashPromotionSession flashPromotionSession = flashPromotionSessionMapper.selectByExample(example).get(0);
            BeanUtils.copyProperties(flashPromotionSession, flashPromotionWithProduct);
        }
        //获取闪购场次下的商品
        flashPromotionWithProduct.setProducts(flashPromotionProductRelationDao.list(flashPromotionWithProduct.getId()));

        return flashPromotionWithProduct;
    }


}
