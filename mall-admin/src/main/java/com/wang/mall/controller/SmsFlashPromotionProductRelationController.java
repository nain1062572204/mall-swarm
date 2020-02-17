package com.wang.mall.controller;

import com.wang.mall.common.api.CommonPage;
import com.wang.mall.common.api.CommonResult;
import com.wang.mall.dto.SmsFlashPromotionProduct;
import com.wang.mall.model.SmsFlashPromotionProductRelation;
import com.wang.mall.service.SmsFlashPromotionProductRelationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 闪购和商品关系Controller
 *
 * @author 王念
 * @create 2020-02-14 17:17
 */
@Api(tags = "SmsFlashPromotionProductRelationController", description = "闪购和商品关系管理")
@RestController
@RequestMapping("/flashProductRelation")
public class SmsFlashPromotionProductRelationController {
    @Autowired
    private SmsFlashPromotionProductRelationService relationService;

    @ApiOperation("批量选择商品关联")
    @PostMapping("/create")
    public CommonResult create(@RequestBody List<SmsFlashPromotionProductRelation> relations) {
        int count = relationService.create(relations);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @ApiOperation("修改关联关系")
    @PutMapping("/update/{id}")
    public CommonResult update(@PathVariable Long id, @RequestBody SmsFlashPromotionProductRelation relation) {
        int count = relationService.update(id, relation);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @ApiOperation("删除关联")
    @DeleteMapping("/delete/{id}")
    public CommonResult delete(@PathVariable Long id) {
        int count = relationService.delete(id);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @ApiOperation("获取指定闪购商品信息")
    @GetMapping("/{id}")
    public CommonResult<SmsFlashPromotionProductRelation> item(@PathVariable Long id) {
        return CommonResult.success(relationService.item(id));
    }

    @ApiOperation("分页获取不同场次及关联商品信息")
    @GetMapping("/list")
    public CommonResult<CommonPage<SmsFlashPromotionProduct>> list(
            @RequestParam("flashPromotionId") Long flashPromotionId,
            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        return CommonResult.success(CommonPage.restPage(relationService.list(flashPromotionId, pageNum, pageSize)));
    }

}
