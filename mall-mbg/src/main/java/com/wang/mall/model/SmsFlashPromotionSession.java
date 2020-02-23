package com.wang.mall.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
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
public class SmsFlashPromotionSession implements Serializable {
    private Long id;

    @ApiModelProperty(value = "开始时间")
    private Date startTime;

    @ApiModelProperty(value = "结束时间")
    private Date endTime;

    @ApiModelProperty(value = "启用状态：0->不启用；1->启用")
    private Integer status;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "场次名称")
    private String name;

    private static final long serialVersionUID = 1L;
}