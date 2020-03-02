package com.wang.mall.front.controller;

import com.wang.mall.common.api.CommonResult;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 王念
 * @create 2020-03-01 22:26
 */
@Api(tags = "UmsCartController", description = "购物车操作")
@RestController
@RequestMapping("/cart")
public class UmsCartController {
    @GetMapping("/index")
    public CommonResult index() {
        return CommonResult.success("idnex");
    }
}
