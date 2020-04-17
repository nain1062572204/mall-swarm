package com.wang.mall.front.controller;

import com.wang.mall.common.api.CommonResult;
import com.wang.mall.front.domain.ConfirmOrderResult;
import com.wang.mall.front.domain.OmsOrderInfoResult;
import com.wang.mall.front.domain.OrderParam;
import com.wang.mall.front.domain.OrderQueryParam;
import com.wang.mall.front.dto.OmsOrderWithItemDTO;
import com.wang.mall.front.exception.OrderNotFoundException;
import com.wang.mall.front.exception.OrderTimeOutException;
import com.wang.mall.front.exception.PmsSkuStockNotFoundException;
import com.wang.mall.front.exception.PmsSkuStockUnderStockException;
import com.wang.mall.front.service.OmsOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class OmsOrderController {
    @Autowired
    private OmsOrderService orderService;

    @ApiOperation("生成确认订单信息")
    @GetMapping("/generateConfirmOrder")
    public CommonResult<ConfirmOrderResult> generateConfirmOrder(@RequestParam("ids") List<Long> ids) {
        return CommonResult.success(orderService.generateConfirmOrder(ids));
    }

    @ApiOperation("生成订单")
    @PostMapping("/generateOrder")
    public CommonResult<String> generateOrder(@RequestBody OrderParam orderParam) {
        try {
            return CommonResult.success(orderService.generateOrder(orderParam));
        } catch (PmsSkuStockNotFoundException e) {
            log.warn(e.getMessage());
            return CommonResult.failed("商品不存在");
        } catch (PmsSkuStockUnderStockException e) {
            log.warn(e.getMessage());
            return CommonResult.failed("商品库存不足");
        }
    }

    @ApiOperation("根据订单号获取订单信息")
    @GetMapping("/{orderSn}")
    public CommonResult<OmsOrderInfoResult> getOrderInfoByOrderSn(@PathVariable String orderSn) {
        return CommonResult.success(orderService.getOrderInfoByOrderSn(orderSn));
    }

    @ApiOperation("查询订单")
    @GetMapping("/list")
    public CommonResult<List<OmsOrderWithItemDTO>> list(@RequestParam(value = "orderType", defaultValue = "0") Integer orderType) {
        return CommonResult.success(orderService.getOrderWithItemByMemberId(orderType));
    }

    @ApiOperation("删除订单")
    @DeleteMapping("/delete/{orderId}")
    public CommonResult<Integer> deleteOrder(@PathVariable("orderId") Long orderId) {
        int count = orderService.deleteOrder(orderId);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed("删除失败");
    }

    @ApiOperation("订单支付")
    @PostMapping("/pay")
    public CommonResult<String> pay(@RequestParam("orderSn") String orderSn) {
        try {
            orderService.payOrder(orderSn);
        } catch (OrderNotFoundException e) {
            log.warn(e.getMessage());
            return CommonResult.failed("订单不存在");
        } catch (OrderTimeOutException e) {
            log.warn(e.getMessage());
            return CommonResult.failed("订单已超时");
        }
        return CommonResult.success(orderSn);
    }
}
