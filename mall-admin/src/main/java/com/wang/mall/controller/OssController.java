package com.wang.mall.controller;

import com.wang.mall.common.api.CommonResult;
import com.wang.mall.dto.OssCallbackResult;
import com.wang.mall.dto.OssPolicyResult;
import com.wang.mall.service.OssService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Oss操作Controller
 *
 * @author 王念
 * @create 2020-02-16 19:23
 */
@Api(tags = "OssController", description = "Oss管理")
@RestController
@RequestMapping("/aliyun/oss")
public class OssController {
    @Autowired
    private OssService ossService;

    @ApiOperation("上传签名生成")
    @GetMapping("/policy")
    public CommonResult<OssPolicyResult> policy() {
        return CommonResult.success(ossService.policy());
    }

    @ApiOperation("oss上传成功回调")
    @PostMapping("/callback")
    public CommonResult<OssCallbackResult> callback(HttpServletRequest request) {
        return CommonResult.success(ossService.callback(request));
    }

}
