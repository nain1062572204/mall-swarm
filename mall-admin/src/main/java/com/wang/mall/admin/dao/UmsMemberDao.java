package com.wang.mall.admin.dao;

import com.wang.mall.model.UmsMember;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户自定义dao
 *
 * @author 王念
 * @create 2020-04-12 0:07
 */
public interface UmsMemberDao {
    List<UmsMember> getRecentlyUser(@Param("recentlyDays") Integer recentlyDays);
}
