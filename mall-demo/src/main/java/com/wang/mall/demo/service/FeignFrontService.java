package com.wang.mall.demo.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author 王念
 * @create 2020-02-06 16:58
 */
@FeignClient("mall-front")
public interface FeignFrontService {
    @GetMapping("/home/content")
    Object content();
}
