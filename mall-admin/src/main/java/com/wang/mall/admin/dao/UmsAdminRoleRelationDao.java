package com.wang.mall.admin.dao;

import com.wang.mall.model.UmsAdminRoleRelation;
import com.wang.mall.model.UmsPermission;
import com.wang.mall.model.UmsResource;
import com.wang.mall.model.UmsRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 王念
 * @create 2020-02-07 18:27
 * 后台角色与用户自定义dao
 */
public interface UmsAdminRoleRelationDao {
    /**
     * 批量插入用户与角色关系
     */
    int insertList(@Param("list") List<UmsAdminRoleRelation> umsAdminRoleRelationList);

    /**
     * 获取用户所有角色
     */
    List<UmsRole> getRoleList(@Param("adminId") Long adminId);

    /**
     * 获取用户所有角色权限
     */
    List<UmsPermission> getRolePermissionList(@Param("adminId") Long adminId);

    /**
     * 获取用户所有权限
     */
    List<UmsPermission> getPermissionList(@Param("adminId") Long adminId);

    /**
     * 获取用户可以访问的资源
     */
    List<UmsResource> getResourceList(@Param("adminId") Long adminId);

    /**
     * 获取资相关用户Id列表
     */
    List<Long> getAdminIdList(@Param("resourceId") Long resourceId);
}
