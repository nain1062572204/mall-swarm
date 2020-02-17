package com.wang.mall.controller;

import com.wang.mall.common.api.CommonPage;
import com.wang.mall.common.api.CommonResult;
import com.wang.mall.dto.PmsProductParam;
import com.wang.mall.dto.PmsProductQueryParam;
import com.wang.mall.dto.PmsProductResult;
import com.wang.mall.model.PmsProduct;
import com.wang.mall.service.PmsProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 王念
 * @create 2020-01-27 20:43
 */
@RestController
@RequestMapping("/product")
@Api(tags = "PmsProductController", description = "商品管理")
public class PmsProductController {
    @Autowired
    private PmsProductService productService;

    @ApiOperation("创建商品")
    @PostMapping("/create")
    @PreAuthorize("hasAnyAuthority('pms:product:create')")
    public CommonResult create(@RequestBody PmsProductParam productParam) {

        int count = productService.create(productParam);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @ApiOperation("根据id获取商品编辑信息")
    @GetMapping("/updateInfo/{id}")
    @PreAuthorize("hasAnyAuthority('pms:product:read')")
    public CommonResult<PmsProductResult> updateInfo(@PathVariable Long id) {
        return CommonResult.success(productService.updateInfo(id));
    }

    @ApiOperation("更新商品")
    @PutMapping("/update/{id}")
    @PreAuthorize("hasAnyAuthority('pms:product:update')")
    public CommonResult update(@PathVariable Long id, @RequestBody PmsProductParam productParam) {
        int count = productService.update(id, productParam);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @ApiOperation("查询商品")
    @GetMapping("/list")
    @PreAuthorize("hasAnyAuthority('pms:product:read')")
    public CommonResult<CommonPage<PmsProduct>> list(PmsProductQueryParam productQueryParam,
                                                     @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                     @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<PmsProduct> products = productService.list(productQueryParam, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(products));
    }

    @ApiOperation("根据商品名称或货号模糊查询")
    @GetMapping("/simpleList")
    @PreAuthorize("hasAnyAuthority('pms:product:read')")
    public CommonResult<List<PmsProduct>> simpleList(String keyword) {
        return CommonResult.success(productService.list(keyword));
    }

    @ApiOperation("批量修改审核状态")
    @PutMapping("/update/verifyStatus")
    @PreAuthorize("hasAnyAuthority('pms:product:update')")
    public CommonResult updateVerifyStatus(@RequestParam("ids") List<Long> ids,
                                           @RequestParam("verifyStatus") Integer verifyStatus,
                                           @RequestParam("detail") String detail
    ) {
        int count = productService.updateVerifyStatus(ids, verifyStatus, detail);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @ApiOperation("批量上下架")
    @PutMapping(value = "/update/publishStatus")
    @PreAuthorize("hasAuthority('pms:product:update')")
    public CommonResult updatePublishStatus(@RequestParam("ids") List<Long> ids,
                                            @RequestParam("publishStatus") Integer publishStatus) {
        int count = productService.updatePublishStatus(ids, publishStatus);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("批量推荐商品")
    @PutMapping(value = "/update/recommendStatus")
    @PreAuthorize("hasAuthority('pms:product:update')")
    public CommonResult updateRecommendStatus(@RequestParam("ids") List<Long> ids,
                                              @RequestParam("recommendStatus") Integer recommendStatus) {
        int count = productService.updateRecommendStatus(ids, recommendStatus);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("批量设为新品")
    @PutMapping(value = "/update/newStatus")
    @PreAuthorize("hasAuthority('pms:product:update')")
    public CommonResult updateNewStatus(@RequestParam("ids") List<Long> ids,
                                        @RequestParam("newStatus") Integer newStatus) {
        int count = productService.updateNewStatus(ids, newStatus);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }


    @ApiOperation("批量修改删除状态")
    @PutMapping(value = "/update/deleteStatus")
    @PreAuthorize("hasAuthority('pms:product:delete')")
    public CommonResult updateDeleteStatus(@RequestParam("ids") List<Long> ids,
                                           @RequestParam("deleteStatus") Integer deleteStatus) {
        int count = productService.updateDeleteStatus(ids, deleteStatus);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }


}
