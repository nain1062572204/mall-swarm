package com.wang.mall.search.domain;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * sku库存信息
 *
 * @author 王念
 * @create 2020-02-26 21:18
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EsSkuStock implements Serializable {
    private static final long serialVersionUID = -2959869538166276437L;
    private Long id;
    private String skuCode;
    private BigDecimal price;
    private String pic;
    private String sp1;
    private String sp2;
    private String sp3;

}
