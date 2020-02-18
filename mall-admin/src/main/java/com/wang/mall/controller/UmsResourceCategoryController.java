package com.wang.mall.controller;

import com.wang.mall.common.api.CommonResult;
import com.wang.mall.model.UmsResourceCategory;
import com.wang.mall.service.UmsResourceCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 后台资源分类管理Controller
 *
 * @author 王念
 * @create 2020-02-18 15:38
 */
@Api(tags = "UmsResourceCategoryController", description = "后台资源分类管理")
@RestController
@RequestMapping("/resourceCategory")
public class UmsResourceCategoryController {
    @Autowired
    private UmsResourceCategoryService resourceCategoryService;

    @ApiOperation("获取所有后台资源分类")
    @GetMapping("/listAll")
    public CommonResult<List<UmsResourceCategory>> listAll() {
        return CommonResult.success(resourceCategoryService.listAll());
    }

    @ApiOperation("添加后台资源分类")
    @PostMapping(value = "/create")
    public CommonResult create(@RequestBody UmsResourceCategory resourceCategory) {
        int count = resourceCategoryService.create(resourceCategory);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("修改后台资源分类")
    @PutMapping(value = "/update/{id}")
    public CommonResult update(@PathVariable Long id,
                               @RequestBody UmsResourceCategory resourceCategory) {
        int count = resourceCategoryService.update(id, resourceCategory);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("根据ID删除后台资源")
    @DeleteMapping(value = "/delete/{id}")
    public CommonResult delete(@PathVariable Long id) {
        int count = resourceCategoryService.delete(id);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }
}
