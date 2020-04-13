package com.wang.mall.front.service.impl;

import com.wang.mall.front.dao.OmsOrderDao;
import com.wang.mall.front.dto.OmsOrderWithItemDTO;
import com.wang.mall.front.factory.OrderTypeServiceFactory;
import com.wang.mall.front.service.OmsOrderTypeService;
import org.apache.catalina.valves.RemoteIpValve;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 关闭订单查询
 *
 * @author 王念
 * @create 2020-04-13 20:02
 */
@Service
public class CanceledOrderTypeServiceImpl implements OmsOrderTypeService, InitializingBean {
    @Autowired
    private OmsOrderDao orderDao;

    @Override
    public List<OmsOrderWithItemDTO> list(Long memberId) {
        return orderDao.getCanceledOrderListWithItem(memberId);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        OrderTypeServiceFactory.register(4, this);
    }
}
