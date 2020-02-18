package com.wang.mall.service;

import com.wang.mall.dto.UmsPermissionNode;
import com.wang.mall.model.UmsPermission;

import java.util.List;

/**
 * @author 王念
 * @create 2020-02-18 14:53
 */
public interface UmsPermissionService {
    /**
     * 添加权限
     */
    int create(UmsPermission permission);

    /**
     * 修改权限
     */
    int update(Long id, UmsPermission permission);

    /**
     * 批量删除权限
     */
    int delete(List<Long> ids);

    /**
     * 以层级结构返回所有权限
     */
    List<UmsPermissionNode> treeList();

    /**
     * 获取所有权限
     */
    List<UmsPermission> list();
}
