package com.wang.mall.admin.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author 王念
 * @create 2020-02-22 13:50
 */
@FeignClient("mall-front")
public interface FeignRedisService {

    @DeleteMapping("/redis/delete/{key}")
    void delete(@PathVariable String key);
}
