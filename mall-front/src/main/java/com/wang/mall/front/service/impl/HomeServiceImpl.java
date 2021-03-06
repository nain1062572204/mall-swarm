package com.wang.mall.front.service.impl;

import com.wang.mall.cache.keys.RedisKeys;
import com.wang.mall.cache.service.RedisService;
import com.wang.mall.front.dao.PmsProductCategoryDao;
import com.wang.mall.front.dao.SmsFlashPromotionProductRelationDao;
import com.wang.mall.front.domain.HomeContentResult;
import com.wang.mall.front.domain.TopBarContentResult;
import com.wang.mall.front.dto.PmsProductCategoryWithChildrenItem;
import com.wang.mall.front.dto.PmsProductCategoryWithProduct;
import com.wang.mall.front.dto.SmsFlashPromotionWithProduct;
import com.wang.mall.front.service.FrontCacheService;
import com.wang.mall.mapper.PmsProductCategoryMapper;
import com.wang.mall.mapper.PmsProductMapper;
import com.wang.mall.mapper.SmsFlashPromotionSessionMapper;
import com.wang.mall.mapper.SmsHomeAdvertiseMapper;
import com.wang.mall.model.*;
import com.wang.mall.front.service.HomeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

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
    /*@Value("${spring.redis.expireTime}")
    private Integer expireTime;*/

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
    private SmsFlashPromotionSessionMapper flashPromotionSessionMapper;
    @Autowired
    private SmsFlashPromotionProductRelationDao flashPromotionProductRelationDao;
    @Autowired
    private PmsProductCategoryDao productCategoryDao;
    @Autowired
    private FrontCacheService frontCacheService;

    @Override
    public HomeContentResult content() {
        //广告信息
        Map<String, List<SmsHomeAdvertise>> advertises = null;
        //推荐商品
        List<PmsProductCategoryWithProduct> recommendProducts = null;
        //秒杀
        SmsFlashPromotionWithProduct flash = null;

        Future<Map<String, List<SmsHomeAdvertise>>> advertiseFuture = executor.submit(this::getAdvertise);
        Future<List<PmsProductCategoryWithProduct>> recommendProductFuture = executor.submit(this::getRecommendProduct);
        Future<SmsFlashPromotionWithProduct> flashFuture = executor.submit(this::getFlash);
        try {
            advertises = advertiseFuture.get();
            recommendProducts = recommendProductFuture.get();
            flash = flashFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        return HomeContentResult.builder()
                .advertises(advertises)
                .recommendProducts(recommendProducts)
                .flashPromotion(flash)
                .build();
    }

    @Override
    public TopBarContentResult topBarContent() {
        List<SmsHomeAdvertise> advertises = null;
        List<PmsProductCategoryWithProduct> categories = null;
        List<PmsProductCategory> navProductCategories = null;
        try {
            categories = executor.submit(this::getHomeCategories).get();
            navProductCategories = executor.submit(this::getNavProductCategory).get();
            advertises = executor.submit(this::getSearchAdvertise).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return TopBarContentResult.builder()
                .categories(categories)
                .navProductCategories(navProductCategories)
                .advertises(advertises)
                .build();
    }

    /**
     * 获取广告
     */
    private Map<String, List<SmsHomeAdvertise>> getAdvertise() {
        final String CAROUSEL = "carousel";
        final String PROMO = "promo";
        final String BANNER = "banner";
        Map<String, List<SmsHomeAdvertise>> result = frontCacheService.getAdvertise();
        if (CollectionUtils.isEmpty(result)) {
            //redis内容为空，从mysql中获取
            SmsHomeAdvertiseExample example = new SmsHomeAdvertiseExample();
            Date currentDate = new Date();
            example.createCriteria()
                    .andStatusEqualTo(1)
                    .andStartTimeLessThan(currentDate)
                    .andEndTimeGreaterThan(currentDate);
            example.setOrderByClause("sort ASC");
            List<SmsHomeAdvertise> advertises = advertiseMapper.selectByExample(example);
            result = new HashMap<>(4);
            result.put(CAROUSEL, advertises.stream().filter(advertise -> advertise.getType() == 0).collect(Collectors.toList()));
            result.put(PROMO, advertises.stream().filter(advertise -> advertise.getType() == 1).collect(Collectors.toList()));
            result.put(BANNER, advertises.stream().filter(advertise -> advertise.getType() == 2).collect(Collectors.toList()));
            redisService.set(RedisKeys.HOME_ADVERTISE.getKey(), result, 60 * 64 * 24);
            return result;
        }
        return result;
    }

    /**
     * 获取推荐商品
     */
    private List<PmsProductCategoryWithProduct> getRecommendProduct() {
        List<PmsProductCategoryWithProduct> result = frontCacheService.getRecommendProduct();
        if (StringUtils.isEmpty(result)) {
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
            //放入redis
            redisService.set(RedisKeys.HOME_PRODUCT.getKey(), result, 60 * 60 * 24);
        }
        return result;
    }

    /**
     * 获取首页分类
     */
    private List<PmsProductCategoryWithProduct> getHomeCategories() {
        List<PmsProductCategoryWithProduct> result = frontCacheService.getHomeCategories();
        if (StringUtils.isEmpty(result)) {
            result = new ArrayList<>();
            //获取商品分类及子分类
            List<PmsProductCategoryWithChildrenItem> productCategoryWithChildrenItems = productCategoryDao.listShowStatusWithChildren();
            for (PmsProductCategoryWithChildrenItem productCategoryWithChildrenItem : productCategoryWithChildrenItems) {
                PmsProductCategoryWithProduct item = new PmsProductCategoryWithProduct();
                item.setId(productCategoryWithChildrenItem.getId());
                item.setName(productCategoryWithChildrenItem.getName());
                PmsProductExample example = new PmsProductExample();
                List<Long> ids = productCategoryWithChildrenItem.getChildren().stream().map(PmsProductCategory::getId).collect(Collectors.toList());
                //子id为空不查询
                if (!CollectionUtils.isEmpty(ids)) {
                    example.createCriteria()
                            .andRecommandStatusEqualTo(1)
                            .andDeleteStatusEqualTo(0)
                            .andPublishStatusEqualTo(1)
                            .andVerifyStatusEqualTo(1)
                            .andProductCategoryIdIn(ids);
                    item.setPmsProducts(productMapper.selectByExample(example));
                    result.add(item);
                }
            }

            redisService.set(RedisKeys.CATEGORY.getKey(), result, 60 * 60 * 24);
            return result;
        }
        return result;
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

    /**
     * 获取导航栏分类
     */
    private List<PmsProductCategory> getNavProductCategory() {
        List<PmsProductCategory> result = frontCacheService.getNavCategoryList();

        if (StringUtils.isEmpty(result)) {
            //从数据库获取导航栏商品分类
            PmsProductCategoryExample example = new PmsProductCategoryExample();
            example.createCriteria()
                    .andNavStatusEqualTo(1);
            example.setOrderByClause("sort ASC");
            result = productCategoryMapper.selectByExample(example);
            //放入redis

            redisService.set(RedisKeys.NAV_CATEGORY.getKey(), result, 60 * 60 * 24);

        }
        return result;
    }

    /**
     * 获取搜索框广告
     */
    private List<SmsHomeAdvertise> getSearchAdvertise() {
        //先读缓存
        List<SmsHomeAdvertise> result = frontCacheService.getSearchAdvertise();
        if (!CollectionUtils.isEmpty(result))
            return result;

        Date currentTime = new Date();
        SmsHomeAdvertiseExample example = new SmsHomeAdvertiseExample();
        example.createCriteria().andTypeEqualTo(3)
                .andStartTimeLessThanOrEqualTo(currentTime)
                .andEndTimeGreaterThanOrEqualTo(currentTime);
        example.setOrderByClause("sort ASC");
        result = advertiseMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(result))
            frontCacheService.setSearchAdvertise(result);
        return result;
    }
}
