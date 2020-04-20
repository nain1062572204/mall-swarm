package com.wang.mall.admin.controller;

import com.wang.mall.common.api.CommonResult;
import com.wang.mall.model.SmsHomeAdvertise;
import com.wang.mall.admin.service.SmsHomeAdvertiseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 王念
 * @create 2020-02-13 23:18
 */
@Api(tags = "SmsHomeAdvertiseController", description = "广告管理")
@RestController
@RequestMapping("/advertise")
public class SmsHomeAdvertiseController {
    @Autowired
    private SmsHomeAdvertiseService advertiseService;

    @ApiOperation(("添加广告"))
    @PostMapping("/create")
    public CommonResult create(@RequestBody SmsHomeAdvertise advertise) {
        int count = advertiseService.create(advertise);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @ApiOperation("删除广告")
    @DeleteMapping("/delete")
    public CommonResult delete(@RequestParam("ids") List<Long> ids) {
        int count = advertiseService.delete(ids);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @ApiOperation("修改上、下线状态")
    @PutMapping("/update/status/{id}")
    public CommonResult updateStatus(@PathVariable Long id, Integer status) {
        int count = advertiseService.updateStatus(id, status);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @ApiOperation(("获取广告详情"))
    @GetMapping("/{id}")
    public CommonResult<SmsHomeAdvertise> item(@PathVariable Long id) {
        return CommonResult.success(advertiseService.item(id));
    }

    @ApiOperation("修改广告")
    @PutMapping("/update/{id}")
    public CommonResult update(@PathVariable Long id, @RequestBody SmsHomeAdvertise advertise) {
        int count = advertiseService.update(id, advertise);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @ApiOperation("分页查询广告")
    @GetMapping("/list")
    public CommonResult<List<SmsHomeAdvertise>> list(
            @RequestParam(value = "type", required = false) Integer type,
            @RequestParam(value = "endTime", required = false) String endTime,
            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum
    ) {
        return CommonResult.success(advertiseService.list(type, endTime, pageSize, pageNum));
    }
}
