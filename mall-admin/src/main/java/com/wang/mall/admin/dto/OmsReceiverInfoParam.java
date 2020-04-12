package com.wang.mall.admin.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author 王念
 * @create 2020-04-12 0:05
 */
@Getter
@Setter
public class OmsReceiverInfoParam {
    private Long orderId;
    private String receiverName;
    private String receiverPhone;
    private String receiverPostCode;
    private String receiverDetailAddress;
    private String receiverProvince;
    private String receiverCity;
    private String receiverRegion;
    private String receiverStreet;
    private Integer status;
}
