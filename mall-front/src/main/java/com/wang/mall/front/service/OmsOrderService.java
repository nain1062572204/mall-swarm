package com.wang.mall.front.service;

import com.wang.mall.front.domain.ConfirmOrderResult;
import com.wang.mall.front.domain.OmsOrderInfoResult;
import com.wang.mall.front.domain.OrderParam;
import com.wang.mall.front.domain.OrderQueryParam;
import com.wang.mall.front.dto.OmsOrderWithItemDTO;

import java.util.List;

/**
 * 订单Service
 *
 * @author 王念
 * @create 2020-03-22 17:05
 */
public interface OmsOrderService {
    /**
     * 确认订单返回信息
     */
    ConfirmOrderResult generateConfirmOrder(List<Long> ids);

    /**
     * 生成订单
     */
    String generateOrder(OrderParam orderParam);

    OmsOrderInfoResult getOrderInfoByOrderSn(String orderSn);

    /**
     * 取消订单
     */
    void cancelOrderByOrderSn(String orderSn);

    /**
     * 根据用户ID查询订单
     */
    List<OmsOrderWithItemDTO> getOrderWithItemByMemberId(Integer orderType);
}
