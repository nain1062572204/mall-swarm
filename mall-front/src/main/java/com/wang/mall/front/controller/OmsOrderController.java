package com.wang.mall.front.controller;

import com.wang.mall.common.api.CommonResult;
import com.wang.mall.front.domain.ConfirmOrderResult;
import com.wang.mall.front.service.OmsOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 王念
 * @create 2020-03-22 17:29
 */
@Api(tags = "OmsOrderController", description = "订单管理")
@RestController
@RequestMapping("/order")
public class OmsOrderController {
    @Autowired
    private OmsOrderService orderService;

    @ApiOperation("生成确认订单信息")
    @GetMapping("/generateConfirmOrder")
    public CommonResult<ConfirmOrderResult> generateConfirmOrder(@RequestParam("ids") List<Long> ids) {
        return CommonResult.success(orderService.generateConfirmOrder(ids));
    }
}
