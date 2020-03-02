package com.wang.mall.front.controller;

import com.wang.mall.common.api.CommonResult;
import com.wang.mall.front.service.UmsMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 王念
 * @create 2020-03-01 22:07
 */
@Api(tags = "UmsMemberController", description = "用户注册登录")
@RestController
@RequestMapping("/sso")
public class UmsMemberController {
    @Autowired
    private UmsMemberService memberService;
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @ApiOperation("用户登录")
    @PostMapping("/login")
    public CommonResult login(@RequestParam String username, @RequestParam String password) {
        String token = memberService.login(username, password);
        if (StringUtils.isEmpty(token))
            return CommonResult.failed("用户名或密码错误");
        Map<String, String> map = new HashMap<>(2);
        map.put("token", token);
        map.put("tokenHead", tokenHead);
        return CommonResult.success(map);
    }
}
