package com.wang.mall.demo.service;

import com.wang.mall.common.api.CommonPage;
import com.wang.mall.common.api.CommonResult;
import com.wang.mall.demo.dto.PmsProductParam;
import com.wang.mall.demo.dto.PmsProductQueryParam;
import com.wang.mall.demo.dto.PmsProductResult;
import com.wang.mall.demo.dto.UmsAdminLoginParam;
import com.wang.mall.model.PmsProduct;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 王念
 * @create 2020-02-12 20:15
 */
@FeignClient("mall-admin")
public interface FeignAdminService {
    @PostMapping("/admin/login")
    CommonResult login(@RequestBody UmsAdminLoginParam loginParam);

    @PostMapping("/product/create")
    CommonResult create(@RequestBody PmsProductParam productParam);


    @GetMapping("/product/updateInfo/{id}")
    CommonResult<PmsProductResult> updateInfo(@PathVariable Long id);

    @PutMapping("/product/update/{id}")
    CommonResult update(@PathVariable Long id, @RequestBody PmsProductParam productParam);


    @GetMapping("/product/list")
    CommonResult<CommonPage<PmsProduct>> list(
            @RequestBody PmsProductQueryParam productQueryParam,
            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum);

    @GetMapping("/product/simpleList")
    CommonResult<List<PmsProduct>> simpleList(String keyword);


    @PutMapping("/product/update/verifyStatus")
    CommonResult updateVerifyStatus(
            @RequestParam("ids") List<Long> ids,
            @RequestParam("verifyStatus") Integer verifyStatus,
            @RequestParam("detail") String detail
    );


    @PutMapping(value = "/product/update/publishStatus")
    CommonResult updatePublishStatus(@RequestParam("ids") List<Long> ids,
                                     @RequestParam("publishStatus") Integer publishStatus);

    @PutMapping(value = "/product/update/recommendStatus")
    CommonResult updateRecommendStatus(@RequestParam("ids") List<Long> ids,
                                       @RequestParam("recommendStatus") Integer recommendStatus);

    @PutMapping(value = "/product/update/newStatus")
    CommonResult updateNewStatus(@RequestParam("ids") List<Long> ids,
                                 @RequestParam("newStatus") Integer newStatus);

    @PutMapping(value = "/product/update/deleteStatus")
    CommonResult updateDeleteStatus(@RequestParam("ids") List<Long> ids,
                                    @RequestParam("deleteStatus") Integer deleteStatus);
}
