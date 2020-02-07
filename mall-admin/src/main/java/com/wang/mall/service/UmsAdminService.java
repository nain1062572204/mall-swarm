package com.wang.mall.service;

import com.wang.mall.model.UmsAdmin;
import com.wang.mall.model.UmsPermission;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

/**
 * @author 王念
 * @create 2020-02-07 16:55
 * 后台管理员Service
 */
public interface UmsAdminService {
    /**
     * 根据用户名获取管理员
     */
    UmsAdmin getAdminByUsername(String username);
    /**
     * 注册
     */
    /**
     * 获取用户所有权限（包括角色权限和+-权限）
     */
    List<UmsPermission> getPermissionList(Long adminId);

    /**
     * 获取用户信息
     */
    UserDetails loadUserByUsername(String username);

    /**
     * 登录
     */
    String login(String username, String password);
}
