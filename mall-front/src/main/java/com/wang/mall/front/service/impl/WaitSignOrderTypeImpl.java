package com.wang.mall.front.service.impl;

import com.wang.mall.front.dao.OmsOrderDao;
import com.wang.mall.front.dto.OmsOrderWithItemDTO;
import com.wang.mall.front.factory.OrderTypeServiceFactory;
import com.wang.mall.front.service.OmsOrderTypeService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 待收货订单查询
 *
 * @author 王念
 * @create 2020-04-13 19:56
 */
@Service
public class WaitSignOrderTypeImpl implements OmsOrderTypeService, InitializingBean {
    @Autowired
    private OmsOrderDao orderDao;

    @Override
    public List<OmsOrderWithItemDTO> list(Long memberId) {
        return orderDao.getWaitSignOrderListWithItem(memberId);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        OrderTypeServiceFactory.register(3, this);
    }
}
