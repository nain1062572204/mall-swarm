package com.wang.mall.admin.service.impl;

import com.wang.mall.admin.dao.OmsOrderDao;
import com.wang.mall.admin.dao.SmsHomeAdvertiseDao;
import com.wang.mall.admin.dao.UmsMemberDao;
import com.wang.mall.admin.dto.HomeContentResult;
import com.wang.mall.admin.service.HomeService;
import com.wang.mall.model.OmsOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.xml.crypto.Data;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.SimpleFormatter;

/**
 * @author 王念
 * @create 2020-04-17 15:49
 */
@Service
public class HomeServiceImpl implements HomeService {
    @Autowired
    private OmsOrderDao orderDao;
    @Autowired
    private UmsMemberDao memberDao;
    @Autowired
    private SmsHomeAdvertiseDao advertiseDao;
    private SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
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
        final HomeContentResult result = new HomeContentResult();
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
        /*
          订单统计
         */
        final HomeContentResult.OrderStatistics orderStatistics = new HomeContentResult.OrderStatistics();
        //查询近七日订单
        final List<OmsOrder> recentlyOrderList = orderDao.getRecentlyOrderList(7);
        //设置本周订单数，即查询结果数
        salesStatistics.setTodayOrderTotal(recentlyOrderList.size());
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
            for (OmsOrder order : recentlyOrderList) {
                if (isToday(order.getCreateTime())) {
                    todaySalesAmount = todaySalesAmount.add(order.getTotalAmount());
                }
                if (isYesterday(order.getCreateTime())) {
                    yesterDaySalesAmount = yesterDaySalesAmount.add(order.getTotalAmount());
                }
                weekSalesAmount = weekSalesAmount.add(order.getTotalAmount());
            }
        }
        waitHandleTransaction.setWaitPayOrderTotal(orderDao.getOrderTotalByStatus(WAIT_PAY_ORDER_TYPE));
        waitHandleTransaction.setWaitDeliverOrderTotal(orderDao.getOrderTotalByStatus(WAIT_DELIVER_ORDER_TYPE));
        waitHandleTransaction.setWaitSignOrderTotal(orderDao.getOrderTotalByStatus(WAIT_SIGN_ORDER_TYPE));
        waitHandleTransaction.setCompleteOrderTotal(orderDao.getOrderTotalByStatus(COMPLETED_ORDER_TYPE));
        waitHandleTransaction.setWaitConfirmOrderTotal(orderDao.getOrderTotalByStatus(WAIT_CONFIRM_ORDER_TYPE));
        waitHandleTransaction.setExpireAdvertiseTotal(advertiseDao.getExpireAdvertise());
        return new HomeContentResult(salesStatistics, waitHandleTransaction, products, users, orderStatistics);
    }

    /**
     * 判断一个时期是否为当前日期
     *
     * @param date 待判断日期
     */
    private boolean isToday(Date date) {

        return sf.format(date).equals(sf.format(new Date()));
    }

    /**
     * 判断一个日期是否是昨日
     */
    private boolean isYesterday(Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, +1);
        return isToday(calendar.getTime());


    }
}
