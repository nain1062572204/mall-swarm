package com.wang.mall.controller;

import com.wang.mall.common.api.CommonResult;
import com.wang.mall.dto.UmsAdminLoginParam;
import com.wang.mall.model.UmsAdmin;
import com.wang.mall.model.UmsRole;
import com.wang.mall.service.UmsAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 王念
 * @create 2020-02-07 18:49
 */
@Api(description = "后台用户管理", tags = "UmsAdminController")
@RestController
@RequestMapping("/admin")
public class UmsAdminController {
    @Autowired
    private UmsAdminService adminService;
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @PostMapping("/login")
    @ApiOperation(value = "用户登录后返回token")
    public CommonResult login(@RequestBody UmsAdminLoginParam adminLoginParam) {
        String token = adminService.login(adminLoginParam.getUsername(), adminLoginParam.getPassword());
        if (StringUtils.isEmpty(token)) {
            return CommonResult.validateFailed("用户名或密码错误");
        }
        Map<String, String> tokenMap = new HashMap<>(4);
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return CommonResult.success(tokenMap);


    }

    @ApiOperation("获取用户信息")
    @GetMapping("/info")
    public CommonResult info(Principal principal) {
        String username = principal.getName();
        UmsAdmin admin = adminService.getAdminByUsername(username);
        Map<String, Object> data = new HashMap<>(4);
        data.put("username", admin.getUsername());
        data.put("roles", adminService.getRoles(admin.getId()));
        data.put("icon", admin.getIcon());
        return CommonResult.success(data);
    }

    @ApiOperation("获取指定用户的角色")
    @GetMapping(value = "/role/{adminId}")
    public CommonResult<List<UmsRole>> getRoleList(@PathVariable Long adminId) {
        List<UmsRole> roleList = adminService.getRoles(adminId);
        return CommonResult.success(roleList);
    }
}
