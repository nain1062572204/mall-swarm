package com.wang.mall.front.domain;

import com.wang.mall.front.dto.PmsProductCategoryWithChildrenItem;
import com.wang.mall.front.dto.PmsProductCategoryWithProduct;
import com.wang.mall.front.dto.SmsFlashPromotionWithProduct;
import com.wang.mall.model.*;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author 王念
 * @create 2020-02-04 21:37
 * 首页内容返回信息封装
 */
@Data
@Builder
public class HomeContentResult implements Serializable {
    private static final long serialVersionUID = -2903766900882022590L;
    //广告信息
    private Map<String, List<SmsHomeAdvertise>> advertises;
    //分类信息
    private List<PmsProductCategoryWithChildrenItem> categories;
    //推荐商品信息
    private List<PmsProductCategoryWithProduct> recommendProducts;
    //秒杀
    private SmsFlashPromotionWithProduct flashPromotion;

}
