package com.wang.mall.controller;

import com.wang.mall.common.api.CommonResult;
import com.wang.mall.model.PmsSkuStock;
import com.wang.mall.service.PmsSkuStockService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * sku库存Controller
 *
 * @author 王念
 * @create 2020-02-15 20:39
 */
@Api(tags = "PmsSkuStockController", description = "sku商品库存管理")
@RestController
@RequestMapping("/sku")
public class PmsSkuStockController {
    @Autowired
    private PmsSkuStockService skuStockService;

    @ApiOperation("根据商品id及编号模糊查询")
    @GetMapping("/{pid}")
    public CommonResult<List<PmsSkuStock>> list(@PathVariable Long pid, @RequestParam(value = "keyword", required = false) String keyword) {
        return CommonResult.success(skuStockService.list(pid, keyword));
    }

    @ApiOperation("批量更新库存信息")
    @PutMapping("/update/{pid}")
    public CommonResult update(@PathVariable Long pid, @RequestBody List<PmsSkuStock> skuStocks) {
        int count = skuStockService.update(pid, skuStocks);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }
}
