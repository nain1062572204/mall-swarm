package com.wang.mall.admin.util;

import cn.hutool.core.util.ArrayUtil;
import com.wang.mall.admin.feign.FeignRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;

/**
 * @author 王念
 * @create 2020-02-23 17:58
 */
@Component
public class RedisUtil {
    @Autowired
    private FeignRedisService redisService;

    /**
     * 删除所有key对应的数据
     */
    public void deleteAll(List<String> keys) {
        if (!CollectionUtils.isEmpty(keys)) {
            for (String key : keys) {
                redisService.delete(key);
            }
        }
    }

    public void deleteAll(String[] keys) {
        if (!ArrayUtil.isEmpty(keys)) {
            deleteAll(Arrays.asList(keys));
        }
    }
}
