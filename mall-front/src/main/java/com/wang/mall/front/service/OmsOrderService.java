package com.wang.mall.front.service;

import com.wang.mall.front.domain.ConfirmOrderResult;

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
}
