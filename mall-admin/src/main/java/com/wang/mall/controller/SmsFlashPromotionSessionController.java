package com.wang.mall.controller;

import com.wang.mall.common.api.CommonPage;
import com.wang.mall.common.api.CommonResult;
import com.wang.mall.dto.SmsFlashPromotionSessionDetail;
import com.wang.mall.model.SmsFlashPromotionSession;
import com.wang.mall.service.SmsFlashPromotionSessionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 闪购场次管理Controller
 *
 * @author 王念
 * @create 2020-02-14 16:58
 */
@Api(tags = "SmsFlashPromotionSessionController", description = "闪购场次管理")
@RestController
@RequestMapping("/flash")
public class SmsFlashPromotionSessionController {
    @Autowired
    private SmsFlashPromotionSessionService promotionSessionService;


    @ApiOperation("添加场次")
    @PostMapping("/create")
    public CommonResult create(@RequestBody SmsFlashPromotionSession promotionSession) {
        int count = promotionSessionService.create(promotionSession);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @ApiOperation("修改场次")
    @PutMapping("/update/{id}")
    public CommonResult update(@PathVariable Long id, @RequestBody SmsFlashPromotionSession promotionSession) {
        int count = promotionSessionService.update(id, promotionSession);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @ApiOperation("修改启用状态")
    @PutMapping("/update/status/{id}")
    public CommonResult updateStatus(@PathVariable Long id, Integer status) {
        int count = promotionSessionService.updateStatus(id, status);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @ApiOperation("获取场次详情")
    @GetMapping("/{id}")
    public CommonResult<SmsFlashPromotionSession> item(@PathVariable Long id) {
        return CommonResult.success(promotionSessionService.item(id));
    }

    @ApiOperation("分页获取全部场次")
    @GetMapping("/list")
    public CommonResult<CommonPage<SmsFlashPromotionSession>> list(
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize
    ) {
        return CommonResult.success(CommonPage.restPage(promotionSessionService.list(pageNum, pageSize)));
    }

    @ApiOperation("获取全部可选场次及其数量")
    @GetMapping("/selectList")
    public CommonResult<List<SmsFlashPromotionSessionDetail>> selectList() {
        return CommonResult.success(promotionSessionService.selectList());
    }

    @ApiOperation("删除场次")
    @DeleteMapping("/delete/{id}")
    public CommonResult delete(@PathVariable Long id) {
        int count = promotionSessionService.delete(id);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }
}
