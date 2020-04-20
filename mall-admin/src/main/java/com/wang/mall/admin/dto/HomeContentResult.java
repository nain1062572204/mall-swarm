package com.wang.mall.admin.dto;


import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 首页数据返回内容
 *
 * @author 王念
 * @create 2020-04-17 14:59
 */
@Getter
@Setter
public class HomeContentResult {
    public HomeContentResult() {
    }

    public HomeContentResult(SalesStatistics salesStatistics, WaitHandleTransaction waitHandleTransaction, Products products, Users users, OrderStatistics orderStatistics) {
        this.salesStatistics = salesStatistics;
        this.waitHandleTransaction = waitHandleTransaction;
        this.products = products;
        this.users = users;
        this.orderStatistics = orderStatistics;
    }

    /**
     * 销售统计
     */
    private SalesStatistics salesStatistics;
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
     * 销售统计类
     */
    @Getter
    @Setter
    public static class SalesStatistics {
        /**
         * 今日订单数
         */
        private int todayOrderTotal;
        /**
         * 今日销售额
         */
        private BigDecimal todaySalesAmount = BigDecimal.ZERO;
        /**
         * 昨日销售额
         */
        private BigDecimal yesterdaySalesAmount = BigDecimal.ZERO;
        /**
         * 近七天销售额
         */
        private BigDecimal weekSalesAmount = BigDecimal.ZERO;

    }

    /**
     * 待处理事务类
     */
    @Getter
    @Setter
    public static class WaitHandleTransaction {
        /**
         * 待付款订单
         */
        private int waitPayOrderTotal;
        /**
         * 待发货定单
         */
        private int waitDeliverOrderTotal;
        /**
         * 待收货订单
         */
        private int waitSignOrderTotal;
        /**
         * 已完成订单
         */
        private int completeOrderTotal;
        /**
         * 新增缺货登记
         */
        private int wantBook;
        /**
         * 待处理退货订单
         */
        private int waitHandleReturnOrderTotal;
        /**
         * 待确认收货订单
         */
        private int waitConfirmOrderTotal;
        /**
         * 待处理退款
         */
        private int waitHandelReturnMoneyOrderTotal;
        /**
         * 即将到期广告位
         */
        private int expireAdvertiseTotal;

    }

    /**
     * 商品总览
     */
    @Getter
    @Setter
    public static class Products {
        /**
         * 已上架
         */
        private int publishTotal;
        /**
         * 已下架
         */
        private int noPublishTotal;
        /**
         * 库存紧张
         */
        private int lowStockTotal;
        /**
         * 所有商品数量
         */
        private int allProductTotal;

    }

    /**
     * 用户总览类
     */
    @Getter
    @Setter
    public static class Users {
        /**
         * 今日新增用户
         */
        private int todayAddTotal;
        /**
         * 昨日新增
         */
        private int yesterdayAddTotal;
        /**
         * 本月新增
         */
        private int monthAddTotal;
        /**
         * 用户总数
         */
        private int allTotal;

    }

    /**
     * 订单统计类
     */
    @Getter
    @Setter
    public static class OrderStatistics {
        /**
         * 订单日期
         */
        private Date date;
        /**
         * 订单数量
         */
        private int orderCount;
        /**
         * 订单总额
         */
        private BigDecimal orderAmount = BigDecimal.ZERO;

    }

}
