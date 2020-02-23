package com.wang.mall.front.controller;

import com.wang.mall.common.api.CommonResult;
import com.wang.mall.front.service.HomeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 王念
 * @create 2020-02-04 19:01
 */
@Api(description = "首页管理控制器", tags = "HomeController")
@RequestMapping("/home")
@RestController
public class HomeController {
    @Autowired
    private HomeService homeService;

    @ApiOperation(value = "获取首页数据")
    @GetMapping("/content")
    public CommonResult homeInfo() {
        return CommonResult.success(homeService.content());
    }

    @ApiOperation("获取导航条数据")
    @GetMapping("/topBar")
    public CommonResult topBar() {
        return CommonResult.success(homeService.topBarContent());
    }
}
