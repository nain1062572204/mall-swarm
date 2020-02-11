package com.wang.mall.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 王念
 * @create 2020-01-27 20:43
 */
@RestController
@RequestMapping("product")
@Api(tags = "PmsProductController", description = "商品管理Controller")
public class PmsProductController {
    @GetMapping("index")
    public String index() {
        return "8180";
    }
}
