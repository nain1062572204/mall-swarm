package com.wang.mall.admin.dao;

import com.wang.mall.model.UmsPermission;
import com.wang.mall.model.UmsRolePermissionRelation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 后台角色管理自定义dao
 *
 * @author 王念
 * @create 2020-02-18 22:06
 */
public interface UmsRolePermissionRelationDao {
    /**
     * 插入角色和权限关系
     */
    int insertList(@Param("list") List<UmsRolePermissionRelation> list);

    /**
     * 根据角色获取权限
     */
    List<UmsPermission> getPermissionList(@Param("roleId") Long roleId);
}
