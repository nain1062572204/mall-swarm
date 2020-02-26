package com.wang.mall.front.domain;

import lombok.*;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author 王念
 * @create 2020-01-28 19:38
 * 搜索中的商品信息
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EsProduct implements Serializable {
    private static final long serialVersionUID = -4475476547105423116L;

    private Long id;
    private String productSn;
    private Long productCategoryId;
    private String productCategoryName;
    private String pic;
    private String name;
    private String subTitle;
    private String keywords;
    private BigDecimal price;
    private Integer sale;
    private Integer newStatus;
    private Integer recommandStatus;
    private Integer stock;
    private Integer promotionType;
    private Integer sort;
    private List<EsProductAttributeValue> attrValueList;

}
