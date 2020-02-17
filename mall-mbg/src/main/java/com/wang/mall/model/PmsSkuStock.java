package com.wang.mall.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;;
import lombok.Builder;;
import lombok.Data;;
import lombok.NoArgsConstructor;;

/**
 * Created by Mybatis Generator 2020-02-17 21:05
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PmsSkuStock implements Serializable {
    private Long id;

    private Long productId;

    @ApiModelProperty(value = "sku编码")
    private String skuCode;

    private BigDecimal price;

    @ApiModelProperty(value = "库存")
    private Integer stock;

    @ApiModelProperty(value = "预警库存")
    private Integer lowStock;

    @ApiModelProperty(value = "销售属性1")
    private String sp1;

    private String sp2;

    private String sp3;

    @ApiModelProperty(value = "展示图片")
    private String pic;

    @ApiModelProperty(value = "销量")
    private Integer sale;

    @ApiModelProperty(value = "单品促销价格")
    private BigDecimal promotionPrice;

    @ApiModelProperty(value = "锁定库存")
    private Integer lockStock;

    private static final long serialVersionUID = 1L;
}