package com.wang.mall.admin.service.impl;

import com.wang.mall.admin.dao.OmsOrderDao;
import com.wang.mall.admin.dao.SmsHomeAdvertiseDao;
import com.wang.mall.admin.dao.UmsMemberDao;
import com.wang.mall.admin.dto.HomeContentResult;
import com.wang.mall.admin.service.HomeService;
import com.wang.mall.admin.util.DateUtil;
import com.wang.mall.mapper.OmsOrderMapper;
import com.wang.mall.mapper.PmsProductMapper;
import com.wang.mall.mapper.UmsMemberMapper;
import com.wang.mall.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 王念
 * @create 2020-04-17 15:49
 */
@Service
public class HomeServiceImpl implements HomeService {
    @Autowired
    private OmsOrderDao orderDao;
    @Autowired
    private OmsOrderMapper orderMapper;
    @Autowired
    private UmsMemberDao memberDao;
    @Autowired
    private PmsProductMapper productMapper;
    @Autowired
    private UmsMemberMapper memberMapper;
    @Autowired
    private SmsHomeAdvertiseDao advertiseDao;
    /**
     * 订单状态：0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单
     */
    private static final int WAIT_PAY_ORDER_TYPE = 0;
    private static final int WAIT_DELIVER_ORDER_TYPE = 1;
    private static final int WAIT_SIGN_ORDER_TYPE = 2;
    private static final int COMPLETED_ORDER_TYPE = 3;
    private static final int WAIT_CONFIRM_ORDER_TYPE = 6;


    @Override

    public HomeContentResult content() {
        /*
          销售统计
         */
        final HomeContentResult.SalesStatistics salesStatistics = new HomeContentResult.SalesStatistics();
        /*
          待处理事务
         */
        final HomeContentResult.WaitHandleTransaction waitHandleTransaction = new HomeContentResult.WaitHandleTransaction();
        /*
          商品总览
         */
        final HomeContentResult.Products products = new HomeContentResult.Products();
        /*
          用户总览
         */
        final HomeContentResult.Users users = new HomeContentResult.Users();
        //查询近七日订单
        final List<OmsOrder> recentlyOrderList = orderDao.getRecentlyOrderList(7);
        if (CollectionUtils.isEmpty(recentlyOrderList)) {
            //如果近7日没有订单，则销售统计均为零
            salesStatistics.setTodayOrderTotal(0);
            salesStatistics.setWeekSalesAmount(BigDecimal.ZERO);
            salesStatistics.setTodaySalesAmount(BigDecimal.ZERO);
            salesStatistics.setYesterdaySalesAmount(BigDecimal.ZERO);
        } else {
            //今日销售额
            BigDecimal todaySalesAmount = BigDecimal.ZERO;
            //昨日销售额
            BigDecimal yesterDaySalesAmount = BigDecimal.ZERO;
            //本周销售额
            BigDecimal weekSalesAmount = BigDecimal.ZERO;
            //今日订单数
            int todayOrderTotal = 0;
            for (OmsOrder order : recentlyOrderList) {
                if (DateUtil.isToday(order.getCreateTime())) {
                    todaySalesAmount = todaySalesAmount.add(order.getTotalAmount());
                    todayOrderTotal++;
                }
                if (DateUtil.isYesterday(order.getCreateTime())) {
                    yesterDaySalesAmount = yesterDaySalesAmount.add(order.getTotalAmount());
                }
                weekSalesAmount = weekSalesAmount.add(order.getTotalAmount());
            }
            salesStatistics.setTodayOrderTotal(todayOrderTotal);
            salesStatistics.setTodaySalesAmount(todaySalesAmount);
            salesStatistics.setYesterdaySalesAmount(yesterDaySalesAmount);
            salesStatistics.setWeekSalesAmount(weekSalesAmount);
        }
        waitHandleTransaction.setWaitPayOrderTotal(orderDao.getOrderTotalByStatus(WAIT_PAY_ORDER_TYPE));
        waitHandleTransaction.setWaitDeliverOrderTotal(orderDao.getOrderTotalByStatus(WAIT_DELIVER_ORDER_TYPE));
        waitHandleTransaction.setWaitSignOrderTotal(orderDao.getOrderTotalByStatus(WAIT_SIGN_ORDER_TYPE));
        waitHandleTransaction.setCompleteOrderTotal(orderDao.getOrderTotalByStatus(COMPLETED_ORDER_TYPE));
        waitHandleTransaction.setWaitConfirmOrderTotal(orderDao.getOrderTotalByStatus(WAIT_CONFIRM_ORDER_TYPE));
        waitHandleTransaction.setExpireAdvertiseTotal(advertiseDao.getExpireAdvertise());
        //获取已上架商品数量
        products.setPublishTotal((int) getPublishedProductTotal());
        //获取已下架商品数量
        products.setNoPublishTotal((int) getNoPublishedProductTotal());
        //获取所有商品数量
        products.setAllProductTotal((int) productMapper.countByExample(new PmsProductExample()));
        //获取最近一个月用户数
        final List<UmsMember> recentlyMemberList = memberDao.getRecentlyUser(30);
        if (CollectionUtils.isEmpty(recentlyMemberList)) {
            users.setTodayAddTotal(0);
            users.setTodayAddTotal(0);
            users.setMonthAddTotal(0);
        } else {
            int todayAdd = 0;
            int yesterdayAdd = 0;
            for (UmsMember member : recentlyMemberList) {
                if (DateUtil.isToday(member.getCreateTime()))
                    todayAdd++;
                if (DateUtil.isYesterday(member.getCreateTime()))
                    yesterdayAdd++;
            }
            users.setTodayAddTotal(todayAdd);
            users.setYesterdayAddTotal(yesterdayAdd);
            users.setMonthAddTotal(recentlyMemberList.size());
        }
        //设置用户总数
        users.setAllTotal((int) getAllMemberTotal());
        //获取订单统计,默认获取最近七天订单
        final List<HomeContentResult.OrderStatistics> orderStatisticsList
                = getOrderStatisticsByDate(DateUtil.getBeforeDate(7), new Date());

        return new HomeContentResult(salesStatistics,
                waitHandleTransaction,
                products,
                users,
                orderStatisticsList);
    }

    /**
     * 查询指定时间范围的订单
     *
     * @param startDate 开始时间
     * @param endDate   结束时间
     * @return
     */
    @Override
    public List<HomeContentResult.OrderStatistics> getOrderStatisticsByDate(Date startDate, Date endDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        OmsOrderExample example = new OmsOrderExample();
        example.createCriteria().
                andStatusEqualTo(3)
                .andDeleteStatusEqualTo(0)
                .andCreateTimeBetween(startDate, endDate);
        final List<OmsOrder> orderList = orderMapper.selectByExample(example);
        final List<String> dayList = DateUtil.getEveryday(dateFormat.format(startDate),dateFormat.format(endDate));
        final List<HomeContentResult.OrderStatistics> result = new ArrayList<>(dayList.size());
        for (String date : dayList) {
            HomeContentResult.OrderStatistics orderStatistics = new HomeContentResult.OrderStatistics();
            orderStatistics.setDate(date);
            for (OmsOrder order : orderList) {
                if (DateUtil.isOneDay(date, order.getCreateTime())) {
                    orderStatistics.setOrderAmount(orderStatistics.getOrderAmount().add(order.getPayAmount()));
                    orderStatistics.setOrderCount(orderStatistics.getOrderCount() + 1);
                }
            }
            result.add(orderStatistics);
        }
        return result;
    }

    private long getAllMemberTotal() {
        return memberMapper.countByExample(new UmsMemberExample());
    }

    private long getNoPublishedProductTotal() {
        PmsProductExample example = new PmsProductExample();
        example.createCriteria().andPublishStatusEqualTo(0);
        return productMapper.countByExample(example);
    }

    private long getPublishedProductTotal() {
        PmsProductExample example = new PmsProductExample();
        example.createCriteria().andPublishStatusEqualTo(1);
        return productMapper.countByExample(example);
    }

}
