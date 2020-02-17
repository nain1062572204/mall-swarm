package com.wang.mall.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
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
public class SmsHomeAdvertise implements Serializable {
    private Long id;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "广告位置：0->首页轮播；1->轮播下的促销；2->中间横向广告")
    private Integer type;

    private String pic;

    @ApiModelProperty(value = "开始时间")
    private Date startTime;

    @ApiModelProperty(value = "结束时间")
    private Date endTime;

    @ApiModelProperty(value = "上下线状态：0->下线；1->上线")
    private Integer status;

    @ApiModelProperty(value = "链接地址")
    private String url;

    @ApiModelProperty(value = "备注")
    private String note;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    private static final long serialVersionUID = 1L;
}