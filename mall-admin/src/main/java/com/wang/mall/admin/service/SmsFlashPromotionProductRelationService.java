package com.wang.mall.admin.service;

import com.wang.mall.admin.dto.SmsFlashPromotionProduct;
import com.wang.mall.model.SmsFlashPromotionProductRelation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 闪购商品场次关系Service
 *
 * @author 王念
 * @create 2020-02-14 15:06
 */
public interface SmsFlashPromotionProductRelationService {
    /**
     * 批量添加关联
     */
    @Transactional
    int create(List<SmsFlashPromotionProductRelation> relations);

    /**
     * 修改关联信息
     */
    int update(Long id, SmsFlashPromotionProductRelation relation);

    /**
     * 删除关联
     */
    int delete(Long id);

    /**
     * 获取关联详情
     */
    SmsFlashPromotionProductRelation item(Long id);

    /**
     * 分页查询商品及促销信息
     *
     * @param id       闪购场次id
     * @param pageNum  页数
     * @param pageSize 单页数据大小
     */
    List<SmsFlashPromotionProduct> list(Long id, Integer pageNum, Integer pageSize);

    /**
     * 根据场次id获取商品关系数量
     *
     * @param id
     */
    long getCount(Long id);
}
