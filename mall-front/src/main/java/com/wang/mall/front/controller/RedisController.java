package com.wang.mall.front.controller;

import com.wang.mall.cache.service.RedisService;
import com.wang.mall.front.bto.RedisKeyValue;
import com.wang.mall.common.api.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 王念
 * @create 2020-02-08 22:05
 */
@Api(tags = "RedisController", description = "Redis缓存管理")
@RestController
@RequestMapping("/redis")
public class RedisController {
    @Autowired
    private RedisService redisService;

    @ApiOperation("添加数据")
    @PostMapping("/set")
    public CommonResult set(@RequestBody RedisKeyValue keyValue) {
        redisService.set(keyValue.getKey(), keyValue.getValue());
        return CommonResult.success("添加成功");
    }

    @ApiOperation("获取数据")
    @PostMapping("/get/{key}")
    public CommonResult get(@PathVariable("key") String key) {
        return CommonResult.success(redisService.get(key));
    }

    @ApiOperation("/删除所有数据")
    @DeleteMapping("/flushAll")
    public CommonResult flushAll() {

        return CommonResult.success(redisService.flushAll());
    }

    @ApiOperation("删除数据")
    @DeleteMapping("/delete/{key}")
    public CommonResult delete(@PathVariable String key) {
        redisService.del(key);
        return CommonResult.success(key);
    }
}
