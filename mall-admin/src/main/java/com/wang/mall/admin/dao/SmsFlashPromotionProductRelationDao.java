package com.wang.mall.admin.dao;

import com.wang.mall.admin.dto.SmsFlashPromotionProduct;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 闪购商品关联自定义dao
 *
 * @author 王念
 * @create 2020-02-14 16:36
 */
public interface SmsFlashPromotionProductRelationDao {
    /**
     * 获取闪购及相关商品信息
     */
    List<SmsFlashPromotionProduct> list(@Param("id") Long id);
}
