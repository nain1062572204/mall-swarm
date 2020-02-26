package com.wang.mall.front.domain;

import lombok.Data;


import java.io.Serializable;

/**
 * @author 王念
 * @create 2020-01-28 19:41
 * 搜索中商品的属性信息
 */
@Data
public class EsProductAttributeValue implements Serializable {
    private static final long serialVersionUID = 9003868595188562833L;
    private Long id;
    private Long productAttributeId;
    //属性值
    private String value;
    //属性参数：0->规格；1->参数
    private Integer type;
    //属性名称
    private String name;
}
