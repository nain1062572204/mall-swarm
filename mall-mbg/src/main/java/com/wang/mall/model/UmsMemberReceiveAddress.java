package com.wang.mall.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Mybatis Generator 2020-03-19 22:49
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UmsMemberReceiveAddress implements Serializable {
    private Long id;

    private Long memberId;

    @ApiModelProperty(value = "收货人名称")
    private String name;

    @ApiModelProperty(value = "手机号码")
    private String phoneNumber;

    @ApiModelProperty(value = "是否为默认:1->默认；0->非默认")
    private Integer defaultStatus;

    @ApiModelProperty(value = "省份/直辖市")
    private String province;

    @ApiModelProperty(value = "城市")
    private String city;

    @ApiModelProperty(value = "区")
    private String region;

    @ApiModelProperty(value = "详细地址（街道）")
    private String detailAddress;

    private static final long serialVersionUID = 1L;
}