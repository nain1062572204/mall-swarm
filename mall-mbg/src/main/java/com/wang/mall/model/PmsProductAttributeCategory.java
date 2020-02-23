package com.wang.mall.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Mybatis Generator 2020-02-22 22:19
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PmsProductAttributeCategory implements Serializable {
    private Long id;

    private String name;

    @ApiModelProperty(value = "属性数量")
    private Integer attributeCount;

    @ApiModelProperty(value = "参数数量")
    private Integer paramCount;

    private static final long serialVersionUID = 1L;
}