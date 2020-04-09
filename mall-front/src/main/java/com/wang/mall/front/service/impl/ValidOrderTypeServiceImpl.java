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
 * 获取有效的订单
 *
 * @author 王念
 * @create 2020-04-08 21:26
 */
@Service
public class ValidOrderTypeServiceImpl implements OmsOrderTypeService, InitializingBean {
    public static final String VALID_ORDER_TYPE = "valid";
    @Autowired
    private OmsOrderDao orderDao;

    @Override
    public List<OmsOrderWithItemDTO> list(Long memberId) {
        //return orderDao.getValidOrderListWithItem(memberId);
        return null;
    }

    @Override
    public void afterPropertiesSet() {
        OrderTypeServiceFactory.register(VALID_ORDER_TYPE, this);
    }
}
