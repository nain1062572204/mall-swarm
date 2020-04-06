package com.wang.mall.front.component;

import com.wang.mall.front.bto.QueueEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 取消订单消息的发出者
 *
 * @author 王念
 * @create 2020-04-05 16:05
 */
@Component
@Slf4j
public class CancelOrderSender {
    @Autowired
    private AmqpTemplate amqpTemplate;

    public void sendMessage(final String orderSn, final long delayTimes) {
        //给延迟队列发消息
        amqpTemplate.convertAndSend(
                QueueEnum.QUEUE_TTL_ORDER_CANCEL.getExchange(),
                QueueEnum.QUEUE_TTL_ORDER_CANCEL.getRouteKey(),
                orderSn,
                message -> {
                    message.getMessageProperties().setExpiration(String.valueOf(delayTimes));
                    return message;
                }
        );
        log.info("send delay message orderSn:{}", orderSn);
    }
}
