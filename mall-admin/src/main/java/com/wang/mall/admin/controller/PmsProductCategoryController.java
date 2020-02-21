package com.wang.mall.admin.controller;

import com.wang.mall.common.api.CommonResult;
import com.wang.mall.admin.dto.PmsProductCategoryParam;
import com.wang.mall.admin.dto.PmsProductCategoryWithChildrenItem;
import com.wang.mall.model.PmsProductCategory;
import com.wang.mall.admin.service.PmsProductCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品分类管理Controller
 *
 * @author 王念
 * @create 2020-02-10 17:43
 */
@Api(tags = "PmsProductCategoryController", description = "商品分类管理")
@RequestMapping("/productCategory")
@RestController
public class PmsProductCategoryController {
    @Autowired
    private PmsProductCategoryService productCategoryService;

    @ApiOperation("添加商品分类")
    @PostMapping("/create")
    //@PreAuthorize("hasAnyAuthority('pms:productCategory:create')")
    public CommonResult create(@Validated @RequestBody PmsProductCategoryParam productCategoryParam, BindingResult result) {
        int count = productCategoryService.create(productCategoryParam);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @ApiOperation("修改商品分类")
    @PutMapping("/update/{id}")
    //@PreAuthorize("hasAnyAuthority('pms:productCategory:update')")
    public CommonResult update(@PathVariable Long id, @Validated @RequestBody PmsProductCategoryParam productCategoryParam, BindingResult result) {
        int count = productCategoryService.update(id, productCategoryParam);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @ApiOperation("分页查询商品分类")
    @GetMapping("/list/{parentId}")
    //@PreAuthorize("hasAnyAuthority('pms:productCategory:read')")
    public CommonResult<List<PmsProductCategory>> list(
            @PathVariable Long parentId,
            @RequestParam(value = "pageSize", defaultValue = "15") Integer pageSize,
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum
    ) {
        List<PmsProductCategory> pmsProductCategories = productCategoryService.list(parentId, pageSize, pageNum);
        return CommonResult.success(pmsProductCategories);

    }

    @ApiOperation("根据Id获取商品分类")
    @GetMapping("/{id}")
    //@PreAuthorize("hasAnyAuthority('pms:productCategory:read')")
    public CommonResult<PmsProductCategory> item(@PathVariable Long id) {
        return CommonResult.success(productCategoryService.item(id));
    }

    @ApiOperation("删除商品分类")
    @DeleteMapping("/delete/{id}")
    //@PreAuthorize("hasAnyAuthority('pms:productCategory:delete')")
    public CommonResult delete(@PathVariable Long id) {
        int count = productCategoryService.delete(id);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @ApiOperation("修改导航栏显示状态")
    @PutMapping("/update/navStatus")
    //@PreAuthorize("hasAnyAuthority('pms:productCategory:update')")
    public CommonResult updateNavStatus(@RequestParam("ids") List<Long> ids, @RequestParam("navStatus") Integer navStatus) {
        int count = productCategoryService.updateNavStatus(ids, navStatus);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }
    @ApiOperation("批量设为推荐")
    @PutMapping(value = "/update/recommandStatus")
    //@PreAuthorize("hasAuthority('pms:product:update')")
    public CommonResult updateRecommandStatus(@RequestParam("ids") List<Long> ids,
                                              @RequestParam("recommandStatus") Integer recommandStatus) {
        int count = productCategoryService.updateRecommandStatus(ids, recommandStatus);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }
    @ApiOperation("修改显示状态")
    @PutMapping("/update/showStatus")
    //@PreAuthorize("hasAnyAuthority('pms:productCategory:update')")
    public CommonResult updateShowStatus(@RequestParam("ids") List<Long> ids, @RequestParam("showStatus") Integer showStatus) {
        int count = productCategoryService.updateShowStatus(ids, showStatus);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @ApiOperation("查询所有一级分类级子分类")
    @GetMapping("/list/withChildren")
    //@PreAuthorize("hasAnyAuthority('pms:productCategory:read')")
    public CommonResult<List<PmsProductCategoryWithChildrenItem>> listWithChildren() {
        return CommonResult.success(productCategoryService.listWithChildren());
    }
}
