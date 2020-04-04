package com.wang.mall.front.domain;

import com.wang.mall.model.OmsOrderItem;
import com.wang.mall.model.UmsMemberReceiveAddress;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 生成订单成功返回信息
 *
 * @author 王念
 * @create 2020-04-04 14:12
 */
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OmsOrderInfoResult implements Serializable {
    private static final long serialVersionUID = 1485239722892598630L;
    //订单号
    private String orderSn;
    //收货信息
    private UmsMemberReceiveAddress address;
    //商品名称列表
    private List<OmsOrderItem> productList;
    //总价
    private BigDecimal total;
    //创建时间
    private Date createTime;

}
