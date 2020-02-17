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
public class UmsMember implements Serializable {
    private Long id;

    private Long memberLeveIdl;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "手机号码")
    private String phone;

    @ApiModelProperty(value = "帐号启用状态:0->禁用；1->启用")
    private Integer status;

    @ApiModelProperty(value = "注册时间")
    private Date createTime;

    @ApiModelProperty(value = "头像")
    private String icon;

    @ApiModelProperty(value = "性别：0->未知；1->男；2->女")
    private Integer gender;

    @ApiModelProperty(value = "生日")
    private Date birthday;

    @ApiModelProperty(value = "所在城市")
    private String city;

    @ApiModelProperty(value = "职业")
    private String job;

    @ApiModelProperty(value = "个性签名")
    private String personalizedSignature;

    @ApiModelProperty(value = "积分")
    private Integer integration;

    @ApiModelProperty(value = "成长值")
    private Integer growth;

    @ApiModelProperty(value = "历史积分数量")
    private Integer historyIntegration;

    private static final long serialVersionUID = 1L;
}