package com.wang.mall.controller;

import com.wang.mall.common.api.CommonPage;
import com.wang.mall.common.api.CommonResult;
import com.wang.mall.model.UmsResource;
import com.wang.mall.security.component.DynamicSecurityMetadataSource;
import com.wang.mall.service.UmsResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 后台资源管理Controller
 *
 * @author 王念
 * @create 2020-02-18 15:52
 */
@Api(tags = "UmsResourceController", description = "后台资源管理")
@RestController
@RequestMapping("/resource")
public class UmsResourceController {
    @Autowired
    private UmsResourceService resourceService;

    @Autowired
    private DynamicSecurityMetadataSource dynamicSecurityMetadataSource;

    @ApiOperation("添加后台资源")
    @PostMapping("/create")
    public CommonResult create(@RequestBody UmsResource resource) {
        int count = resourceService.create(resource);
        dynamicSecurityMetadataSource.clearDataSource();
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @ApiOperation("修改后台资源")
    @PutMapping("/update/{id}")
    public CommonResult update(@PathVariable Long id, @RequestBody UmsResource resource) {
        int count = resourceService.update(id, resource);
        dynamicSecurityMetadataSource.clearDataSource();
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @ApiOperation("根据id获取资源详情")
    @GetMapping("/{id}")
    public CommonResult<UmsResource> item(@PathVariable Long id) {
        return CommonResult.success(resourceService.getItem(id));
    }

    @ApiOperation("根据id删除资源")
    @DeleteMapping("/delete/{id}")
    public CommonResult delete(@PathVariable Long id) {
        int count = resourceService.delete(id);
        dynamicSecurityMetadataSource.clearDataSource();
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @ApiOperation("分页模糊查询后台资源")
    @GetMapping("/list")
    public CommonResult<CommonPage<UmsResource>> list(@RequestParam(required = false) Long categoryId,
                                                      @RequestParam(required = false) String nameKeyword,
                                                      @RequestParam(required = false) String urlKeyword,
                                                      @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                      @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        return CommonResult.success(CommonPage.restPage(resourceService.list(categoryId, nameKeyword, urlKeyword, pageSize, pageNum)));
    }

    @ApiOperation("查询所有后台资源")
    @GetMapping("/listAll")
    public CommonResult<List<UmsResource>> listAll() {
        return CommonResult.success(resourceService.listAll());
    }
}

