package com.wang.mall.admin.dao;

import com.wang.mall.admin.dto.OmsOrderDeliveryParam;
import com.wang.mall.admin.dto.OmsOrderDetail;
import com.wang.mall.admin.dto.OmsOrderQueryParam;
import com.wang.mall.model.OmsOrder;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;

import java.util.List;

/**
 * @author 王念
 * @create 2020-04-12 0:10
 */
public interface OmsOrderDao {
    /**
     * 条件查询订单
     */
    List<OmsOrder> getList(@Param("queryParam") OmsOrderQueryParam queryParam);

    /**
     * 批量发货
     */
    int delivery(@Param("list") List<OmsOrderDeliveryParam> deliveryParamList);

    /**
     * 获取订单详情
     */
    OmsOrderDetail getDetail(@Param("id") Long id);

    /**
     * @param recentlyDays 最近几天
     */
    List<OmsOrder> getRecentlyOrderList(@Param("recentlyDays") Integer recentlyDays);

    /**
     * 根据订单状态获取订单数量
     */
    Integer getOrderTotalByStatus(@Param("status") Integer status);
}
