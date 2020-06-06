package com.wang.admin;

import com.wang.mall.admin.AdminApplication;
import com.wang.mall.admin.service.OmsOrderService;
import com.wang.mall.model.OmsOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest(classes = {AdminApplication.class})
@RunWith(SpringRunner.class)
public class OmsOrderDaoTest {
    @Autowired
    private OmsOrderService orderService;
    @Test
    public void test() {
        List<OmsOrder> orderList = orderService.getRecentlyOrderList(30);
        orderList.forEach(System.out::println);
    }
}
