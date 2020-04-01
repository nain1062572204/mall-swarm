package com.wang.mall.front.dao;

import com.wang.mall.model.OmsOrderItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 订单中的商品自定义dao
 *
 * @author 王念
 * @create 2020-03-31 23:12
 */
public interface OmsOrderItemDao {
    Integer insertList(@Param("items") List<OmsOrderItem> items);
}
