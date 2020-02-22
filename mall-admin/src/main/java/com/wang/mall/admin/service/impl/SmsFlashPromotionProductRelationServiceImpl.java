package com.wang.mall.admin.service.impl;

import com.github.pagehelper.PageHelper;
import com.wang.mall.admin.dao.SmsFlashPromotionProductRelationDao;
import com.wang.mall.admin.dto.SmsFlashPromotionProduct;
import com.wang.mall.admin.feign.FeignRedisService;
import com.wang.mall.common.rediskey.RedisKeys;
import com.wang.mall.mapper.SmsFlashPromotionProductRelationMapper;
import com.wang.mall.model.SmsFlashPromotionProductRelation;
import com.wang.mall.model.SmsFlashPromotionProductRelationExample;
import com.wang.mall.admin.service.SmsFlashPromotionProductRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 闪购商品关系Service实现类
 *
 * @author 王念
 * @create 2020-02-14 15:13
 */
@Service
public class SmsFlashPromotionProductRelationServiceImpl implements SmsFlashPromotionProductRelationService {
    @Autowired
    private SmsFlashPromotionProductRelationMapper relationMapper;
    @Autowired
    private SmsFlashPromotionProductRelationDao relationDao;
    @Autowired
    private FeignRedisService feignRedisService;

    @Override
    public int create(List<SmsFlashPromotionProductRelation> relations) {
        feignRedisService.delete(RedisKeys.FLASH_PROMOTION.getKey());
        for (SmsFlashPromotionProductRelation relation : relations) {
            relationMapper.insert(relation);
        }
        return relations.size();
    }

    @Override
    public int update(Long id, SmsFlashPromotionProductRelation relation) {
        feignRedisService.delete(RedisKeys.FLASH_PROMOTION.getKey());
        relation.setId(id);
        return relationMapper.updateByPrimaryKeySelective(relation);
    }

    @Override
    public int delete(Long id) {
        feignRedisService.delete(RedisKeys.FLASH_PROMOTION.getKey());
        return relationMapper.deleteByPrimaryKey(id);
    }

    @Override
    public SmsFlashPromotionProductRelation item(Long id) {
        return relationMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<SmsFlashPromotionProduct> list(Long id, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return relationDao.list(id);
    }

    @Override
    public long getCount(Long id) {
        SmsFlashPromotionProductRelationExample example = new SmsFlashPromotionProductRelationExample();
        example.createCriteria()
                .andFlashPromotionSessionIdEqualTo(id);
        return relationMapper.countByExample(example);
    }
}
