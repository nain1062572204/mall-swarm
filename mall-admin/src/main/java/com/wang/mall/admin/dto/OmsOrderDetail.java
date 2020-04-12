package com.wang.mall.admin.dto;

import com.wang.mall.model.OmsOrder;
import com.wang.mall.model.OmsOrderItem;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 订单详情信息
 *
 * @author 王念
 * @create 2020-04-12 0:03
 */
@Getter
@Setter
public class OmsOrderDetail extends OmsOrder {
    private static final long serialVersionUID = -7544254065946442287L;
    private List<OmsOrderItem> orderItemList;
}
