package com.wang.mall.front.domain;

import com.wang.mall.model.UmsMemberReceiveAddress;
import lombok.*;

import java.util.List;

/**
 * @author 王念
 * @create 2020-03-22 16:45
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderParam {
    //收货信息
    private UmsMemberReceiveAddress address;
    //订单备注
    private String note;
    //包含的商品信息
    List<ProductInfo> productInfos;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    public static class ProductInfo {
        //库存Id
        private Long productSkuId;
        //购买数量
        private Integer quantity;
        //商品名
        private String productName;
        //商品码
        private String productSn;
        private Long productId;
        private String productPic;
        //销售属性
        private String productAttr;


    }
}

