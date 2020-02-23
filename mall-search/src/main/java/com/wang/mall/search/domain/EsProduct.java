package com.wang.mall.search.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author 王念
 * @create 2020-01-28 19:38
 * 搜索中的商品信息
 */
@Builder
@Document(indexName = "pms", type = "product", shards = 1, replicas = 0)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EsProduct implements Serializable {
    private static final long serialVersionUID = -4475476547105423116L;

    @Id
    private Long id;
    @Field(type = FieldType.Keyword)
    private String productSn;
    private Long productCategoryId;
    @Field(type = FieldType.Keyword)
    private String productCategoryName;
    private String pic;
    @Field(analyzer = "ik_max_word", type = FieldType.Text)
    private String name;
    @Field(analyzer = "ik_max_word", type = FieldType.Text)
    private String subTitle;
    @Field(analyzer = "ik_max_word", type = FieldType.Text)
    private String keywords;
    private BigDecimal price;
    private Integer sale;
    private Integer newStatus;
    private Integer recommandStatus;
    private Integer stock;
    private Integer promotionType;
    private Integer sort;
    @Field(type = FieldType.Nested)
    private List<EsProductAttributeValue> attrValueList;

}
