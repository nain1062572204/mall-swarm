package com.wang.mall.front.dao;

import com.wang.mall.front.domain.OmsOrderInfoResult;
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
    List<OmsOrderWithItemDTO> getOrderListWithItem(@Param("memberId") Long memberId, @Param("keyword") String keyword);
}
