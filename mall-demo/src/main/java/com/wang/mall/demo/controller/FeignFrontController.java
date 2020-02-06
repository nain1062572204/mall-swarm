package com.wang.mall.demo.controller;

import com.wang.mall.demo.service.FeignFrontService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 王念
 * @create 2020-02-06 17:00
 */
@Api(tags = "FeignFrontController", description = "Feign调用mall-front接口实例")
@RestController
@RequestMapping("/feign/front")
public class FeignFrontController {
    @Autowired
    private FeignFrontService feignFrontService;

    @GetMapping("/content")
    @ApiOperation("获取首页内容")
    public Object content() {
        return feignFrontService.content();
    }
}
