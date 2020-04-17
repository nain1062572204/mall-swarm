package com.wang.mall.admin.service.impl;

import com.wang.mall.admin.dao.OmsOrderDao;
import com.wang.mall.admin.dao.UmsMemberDao;
import com.wang.mall.admin.dto.HomeContentResult;
import com.wang.mall.admin.service.HomeService;
import com.wang.mall.model.OmsOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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

    @Override
    public HomeContentResult content() {

        //查询近七日订单
        final List<OmsOrder> recentlyOrderList = orderDao.getRecentlyOrderList(7);
        if (CollectionUtils.isEmpty(recentlyOrderList)) {

        }
        final List<OmsOrder> todayOrderList = null;

        return null;
    }

    /**
     * 判断一个时期是否为当前日期
     *
     * @param date 待判断日期
     */
    private boolean isToday(Date date) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
        return sf.format(date).equals(sf.format(new Date()));
    }


}
