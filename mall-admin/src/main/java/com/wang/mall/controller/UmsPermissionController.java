package com.wang.mall.controller;

import com.wang.mall.common.api.CommonResult;
import com.wang.mall.dto.UmsPermissionNode;
import com.wang.mall.model.UmsPermission;
import com.wang.mall.service.UmsPermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 后台权限管理
 *
 * @author 王念
 * @create 2020-02-18 15:01
 */
@Api(tags = "UmsPermissionController", description = "后台用户权限管理")
@RestController
@RequestMapping("/permission")
public class UmsPermissionController {
    @Autowired
    private UmsPermissionService permissionService;

    @ApiOperation("添加权限")
    @PostMapping("/create")
    public CommonResult create(@RequestBody UmsPermission permission) {
        int count = permissionService.create(permission);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @ApiOperation("修改权限")
    @PutMapping("/update/{id}")
    public CommonResult update(@PathVariable Long id, @RequestBody UmsPermission permission) {
        int count = permissionService.update(id, permission);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @ApiOperation("根据id批量删除权限")
    @DeleteMapping("/delete")
    public CommonResult delete(@RequestParam("ids") List<Long> ids) {
        int count = permissionService.delete(ids);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @ApiOperation("以层级结构返回所有权限")
    @GetMapping("/treeList")
    public CommonResult<List<UmsPermissionNode>> treeList() {
        return CommonResult.success(permissionService.treeList());
    }

    @ApiOperation("获取所有权限列表")
    @GetMapping("/list")
    public CommonResult<List<UmsPermission>> list() {
        return CommonResult.success(permissionService.list());
    }

}
