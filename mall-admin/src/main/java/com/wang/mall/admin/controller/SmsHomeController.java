package com.wang.mall.admin.controller;

import com.wang.mall.admin.dto.HomeContentResult;
import com.wang.mall.admin.service.HomeService;
import com.wang.mall.common.api.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 后台管理首页Controller
 *
 * @author 王念
 * @create 2020-04-19 21:57
 */
@Api(tags = "SmsHomeController", description = "首页内容管理")
@RestController
@RequestMapping("/home")
public class SmsHomeController {
    @Autowired
    private HomeService homeService;

    @ApiOperation("获取首页信息")
    @GetMapping("/content")
    public CommonResult<HomeContentResult> content() {
        return CommonResult.success(homeService.content());
    }
}
