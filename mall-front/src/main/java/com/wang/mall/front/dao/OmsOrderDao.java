package com.wang.mall.front.dao;

import com.wang.mall.front.domain.OmsOrderInfoResult;
import org.apache.ibatis.annotations.Param;

/**
 * 订单自定义dao
 *
 * @author 王念
 * @create 2020-04-04 16:04
 */
public interface OmsOrderDao {
    OmsOrderInfoResult getOrderInfoByOrderSn(@Param("orderSn") String orderSn);
}
