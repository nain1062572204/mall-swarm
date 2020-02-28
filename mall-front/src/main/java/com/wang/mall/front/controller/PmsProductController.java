package com.wang.mall.front.controller;

import com.wang.mall.common.api.CommonResult;
import com.wang.mall.front.service.PmsProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 王念
 * @create 2020-02-28 18:04
 */
@Api(tags = "PmsProductController", description = "商品操作")
@RestController
@RequestMapping("/product")
public class PmsProductController {
    @Autowired
    private PmsProductService productService;

    @ApiOperation("获取商品详情")
    @GetMapping("/detail/{id}")
    public CommonResult detail(@PathVariable("id") Long id) {
        return CommonResult.success(productService.getProductDetail(id));
    }
}
