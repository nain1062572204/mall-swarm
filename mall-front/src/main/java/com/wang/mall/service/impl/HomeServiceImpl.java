package com.wang.mall.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wang.mall.domain.HomeContentResult;
import com.wang.mall.mapper.PmsProductCategoryMapper;
import com.wang.mall.mapper.SmsHomeAdvertiseMapper;
import com.wang.mall.model.PmsProductCategory;
import com.wang.mall.model.PmsProductCategoryExample;
import com.wang.mall.service.HomeService;
import com.wang.mall.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;


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
    @Value("${spring.redis.key.homeBanner}")
    private String REDIS_KEY_HOME_BANNER;
    @Value("${spring.redis.key.homePromo}")
    private String REDIS_KEY_HOME_PROMO;
    @Value("${spring.redis.key.falshPormotion}")
    private String REDIS_KEY_FLASH_PROMOTION;
    @Value("${spring.redis.key.homeProduct}")
    private String REDIS_KEY_HOME_PRODUCT;

    @Autowired
    private ThreadPoolTaskExecutor executor;
    @Autowired
    private RedisService redisService;
    @Autowired
    private SmsHomeAdvertiseMapper advertiseMapper;
    @Autowired
    private PmsProductCategoryMapper productCategoryMapper;

    @Override
    public HomeContentResult content() {
        HomeContentResult contentResult = new HomeContentResult();
        ObjectMapper mapper = new ObjectMapper();
        //获取轮播图
        String categoryStr = redisService.get(REDIS_KEY_CATEGORY);
        System.out.println(categoryStr);
        if (StringUtils.isEmpty(categoryStr)) {
            //读mysql
            Future<List<PmsProductCategory>> categories = executor.submit(() -> getHomeProductCategory());
            //放入redis
            try {
                redisService.set(REDIS_KEY_CATEGORY, mapper.writeValueAsString(categories.get()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (JsonProcessingException e) {
                log.error("对象转换异常:{}", e.getMessage());
            }
        }
        return contentResult;
    }

    private List<PmsProductCategory> getHomeProductCategory() {
        PmsProductCategoryExample example = new PmsProductCategoryExample();
        example.createCriteria()
                .andShowStatusEqualTo(1)
                .andNavStatusEqualTo(1);
        List<PmsProductCategory> advertises = productCategoryMapper.selectByExample(example);
        return advertises;
    }
}
