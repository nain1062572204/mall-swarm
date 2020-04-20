package com.wang.mall.admin.service;

import com.wang.mall.admin.dto.HomeContentResult;

import java.util.Date;
import java.util.List;

/**
 * 首页内容service
 *
 * @author 王念
 * @create 2020-04-17 20:47
 */
public interface HomeService {
    HomeContentResult content();

    List<HomeContentResult.OrderStatistics> getOrderStatisticsByDate(Date startDate, Date endDate);
}
