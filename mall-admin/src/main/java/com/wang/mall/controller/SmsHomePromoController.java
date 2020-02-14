package com.wang.mall.controller;

import com.wang.mall.common.api.CommonResult;
import com.wang.mall.model.SmsHomePromo;
import com.wang.mall.service.SmsHomePromoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 首页促销管理Controller
 *
 * @author 王念
 * @create 2020-02-14 14:15
 */
@Api(tags = "SmsHomePromoController", description = "首页促销管理")
@RestController
@RequestMapping("/promo")
public class SmsHomePromoController {
    @Autowired
    private SmsHomePromoService promoService;

    @ApiOperation("创建促销")
    @PostMapping("/create")
    public CommonResult create(@RequestBody SmsHomePromo promo) {
        int count = promoService.create(promo);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @ApiOperation("删除促销")
    @DeleteMapping("/delete")
    public CommonResult delete(@RequestParam("ids") List<Long> ids) {
        int count = promoService.delete(ids);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @ApiOperation("修改上下线状态")
    @PutMapping("/update/status/{id}")
    public CommonResult updateStatus(@PathVariable Long id, Integer status) {
        int count = promoService.updateStatus(id, status);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @ApiOperation("获取促销详情")
    @GetMapping("/{id}")
    public CommonResult<SmsHomePromo> item(@PathVariable Long id) {
        return CommonResult.success(promoService.item(id));
    }

    @ApiOperation("分页查询促销")
    @GetMapping("/list")
    public CommonResult<List<SmsHomePromo>> list(
            @RequestParam(value = "endTime", required = false) String endTime,
            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        return CommonResult.success(promoService.list(endTime, pageSize, pageNum));
    }
}
