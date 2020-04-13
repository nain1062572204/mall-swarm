package com.wang.mall.front.dao;

import com.wang.mall.front.domain.OmsOrderInfoResult;
import com.wang.mall.front.domain.OrderQueryParam;
import com.wang.mall.front.dto.OmsOrderWithItemDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 订单自定义dao
 *
 * @author 王念
 * @create 2020-04-04 16:04
 */
public interface OmsOrderDao {
    OmsOrderInfoResult getOrderInfoByOrderSn(@Param("orderSn") String orderSn);

    /**
     * 查询订单
     */
    List<OmsOrderWithItemDTO> getOrderListWithItem(@Param("memberId") Long memberId, @Param("orderQueryParam") OrderQueryParam orderQueryParam);

    /**
     * 查询有效订单
     */
    List<OmsOrderWithItemDTO> getValidOrderListWithItem(@Param("memberId") Long memberId);

    /**
     * 查询待支付订单
     */
    List<OmsOrderWithItemDTO> getWaitPayOrderListWithItem(@Param("memberId") Long memberId);

    /**
     * 查询待发货订单
     */
    List<OmsOrderWithItemDTO> getWaitDeliverOrderListWithItem(@Param("memberId") Long memberId);

    /**
     * 查询待签收订单
     */
    List<OmsOrderWithItemDTO> getWaitSignOrderListWithItem(@Param("memberId") Long memberId);

    /**
     * 查询已取消订单
     */
    List<OmsOrderWithItemDTO> getCanceledOrderListWithItem(@Param("memberId") Long memberId);
}
