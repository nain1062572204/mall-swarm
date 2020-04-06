package com.wang.mall.front.component;

import com.wang.mall.front.service.OmsOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author 王念
 * @create 2020-04-05 16:11
 */
@Component
@Slf4j
@RabbitListener(queues = "mall.order.cancel")
public class CancelOrderReceiver {
    @Autowired
    private OmsOrderService orderService;

    @RabbitHandler
    public void handel(String orderSn) {
        log.info("receive delay message orderSn:{}", orderSn);
        //TODO 调用取消订单的服务
        orderService.cancelOrderByOrderSn(orderSn);
    }
}
