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
public class PmsProductVerifyRecord implements Serializable {
    private Long id;

    private Long productId;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "审核人")
    private String verifyMan;

    private Integer status;

    @ApiModelProperty(value = "反馈详情")
    private String detail;

    private static final long serialVersionUID = 1L;
}