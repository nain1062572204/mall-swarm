package com.wang.mall.front.factory;

import com.wang.mall.front.service.OmsOrderTypeService;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 王念
 * @create 2020-04-08 21:27
 */
public class OrderTypeServiceFactory {
    private static final Map<Integer, OmsOrderTypeService> services = new ConcurrentHashMap<>(8);

    private OrderTypeServiceFactory() {
    }

    public static OmsOrderTypeService getOmsOrderTypeServiceByType(Integer orderType) {
        return services.get(orderType);
    }

    public static void register(Integer orderType, OmsOrderTypeService orderTypeService) {
        if (StringUtils.isEmpty(orderType))
            throw new RuntimeException("userType can't be null");
        services.put(orderType, orderTypeService);
    }
}
