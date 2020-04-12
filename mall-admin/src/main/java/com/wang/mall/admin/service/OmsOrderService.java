package com.wang.mall.admin.service;

import com.wang.mall.admin.dto.OmsOrderDeliveryParam;
import com.wang.mall.admin.dto.OmsOrderDetail;
import com.wang.mall.admin.dto.OmsOrderQueryParam;
import com.wang.mall.admin.dto.OmsReceiverInfoParam;
import com.wang.mall.model.OmsOrder;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 订单管理Service
 *
 * @author 王念
 * @create 2020-04-11 23:52
 */
public interface OmsOrderService {
    /**
     * 订单查询
     */
    List<OmsOrder>list (OmsOrderQueryParam orderQueryParam,Integer pageSize, Integer pageNum);
    /**
     * 批量发货
     */
    @Transactional
    int delivery(List<OmsOrderDeliveryParam> deliveryParamList);
    /**
     * 批量删除
     */
    int delete(List<Long> ids);
    /**
     * 批量关闭订单
     */
    @Transactional
    int close(List<Long> ids);
    /**
     * 获取指定订单详情
     */
    OmsOrderDetail detail(Long id);
    /**
     * 修改订单收货人信息
     */
    @Transactional
    int updateReceiverInfo(OmsReceiverInfoParam receiverInfoParam);



    /**
     * 修改订单备注
     */
    @Transactional
    int updateNote(Long id, String note, Integer status);

}
