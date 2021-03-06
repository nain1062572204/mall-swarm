package com.wang.mall.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Mybatis Generator 2020-04-01 18:49
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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