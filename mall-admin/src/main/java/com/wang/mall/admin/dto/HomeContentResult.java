package com.wang.mall.admin.dto;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 首页数据返回内容
 *
 * @author 王念
 * @create 2020-04-17 14:59
 */
@Data
@Builder
@NoArgsConstructor
public class HomeContentResult {
    /**
     * 今日订单数
     */
    private Integer todayOrderTotal;
    /**
     * 今日销售额
     */
    private BigDecimal todaySalesAmount;
    /**
     * 昨日销售额
     */
    private BigDecimal yesterdaySalesAmount;
    /**
     * 待处理事务
     */
    private WaitHandleTransaction waitHandleTransaction;
    /**
     * 商品总览
     */
    private Products products;
    /**
     * 用户总览
     */
    private Users users;
    /**
     * 订单统计
     */
    private OrderStatistics orderStatistics;

    /**
     * 待处理事务类
     */
    @Builder
    @Data
    static class WaitHandleTransaction {
        /**
         * 待付款订单
         */
        private Integer waitPayOrderTotal;
        /**
         * 待发货定单
         */
        private Integer waitDeliverOrderTotal;
        /**
         * 待收货订单
         */
        private Integer waitSignOrderTotal;
        /**
         * 已完成订单
         */
        private Integer completeOrderTotal;
        /**
         * 新增缺货登记
         */
        private Integer wantBook;
        /**
         * 待处理退货订单
         */
        private Integer waitHandleReturnOrderTotal;
        /**
         * 待确认收货订单
         */
        private Integer waitConfirmOrderTotal;
        /**
         * 待处理退款
         */
        private Integer waitHandelReturnMoneyOrderTotal;
        /**
         * 即将到期广告位
         */
        private Integer expireAdvertiseTotal;
    }

    /**
     * 商品总览
     */
    @Builder
    @Data
    static class Products {
        /**
         * 已上架
         */
        private Integer publishTotal;
        /**
         * 已下架
         */
        private Integer noPublishTotal;
        /**
         * 库存紧张
         */
        private Integer lowStockTotal;
        /**
         * 所有商品数量
         */
        private Integer allProductTotal;
    }

    /**
     * 用户总览类
     */
    @Builder
    @Data
    static class Users {
        /**
         * 今日新增用户
         */
        private Integer todayAddTotal;
        /**
         * 昨日新增
         */
        private Integer yesterdayAddTotal;
        /**
         * 本月新增
         */
        private Integer monthAddTotal;
        /**
         * 用户总数
         */
        private Integer allTotal;

    }

    /**
     * 订单统计类
     */
    @Builder
    @Data
    static class OrderStatistics {
        /**
         * 订单日期
         */
        private Date date;
        /**
         * 订单数量
         */
        private Integer orderCount;
        /**
         * 订单总额
         */
        private BigDecimal orderAmount;
    }

}
