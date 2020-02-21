package com.wang.mall.front.bto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author 王念
 * @create 2020-02-08 22:07
 */
@Getter
@Setter
public class RedisKeyValue {
    @ApiModelProperty("key")
    String key;
    @ApiModelProperty("value")
    String value;
}
