package com.wang.mall.front.dto;

import com.wang.mall.model.OmsOrder;
import com.wang.mall.model.OmsOrderItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * 订单及其包含商品DTO
 *
 * @author 王念
 * @create 2020-04-06 15:24
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OmsOrderWithItemDTO extends OmsOrder {
    private static final long serialVersionUID = -4847188016259647181L;
    private List<OmsOrderItem> orderItemList;
}
