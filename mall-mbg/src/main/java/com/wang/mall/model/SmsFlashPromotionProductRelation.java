package com.wang.mall.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Builder;;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
* Created by Mybatis Generator 2020-02-17 16:00
*/
@Getter
@Setter
@Data
@Builder
@ToString
public class SmsFlashPromotionProductRelation implements Serializable {
    private Long id;

    private Long flashPromotionSessionId;

    private Long productId;

    @ApiModelProperty(value = "闪购价格")
    private BigDecimal flashPromotionPrice;

    @ApiModelProperty(value = "闪购数量")
    private Integer flashPromotionCount;

    @ApiModelProperty(value = "限购数量")
    private Integer flashPromotionLimit;

    private Integer sort;

    private static final long serialVersionUID = 1L;
}