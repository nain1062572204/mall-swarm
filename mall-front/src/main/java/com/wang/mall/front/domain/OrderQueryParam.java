package com.wang.mall.front.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * 订单查询参数
 *
 * @author 王念
 * @create 2020-04-08 21:03
 */
@Data
public class OrderQueryParam {
    @ApiModelProperty(value = "订单号")
    private String orderSn;
    @ApiModelProperty(value = "订单类型：0->全部有效订单；1->待付款；2->待发货；3->待收货；4->已关闭")
    @NotEmpty(message = "订单状态不能为空")
    private Integer orderType;
    @ApiModelProperty(value = "keyword")
    private String keyword;
}
