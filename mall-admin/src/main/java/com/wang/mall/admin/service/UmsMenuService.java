package com.wang.mall.admin.service;

import com.wang.mall.admin.dto.UmsMenuNode;
import com.wang.mall.model.UmsMenu;

import java.util.List;

/**
 * 后台菜单管理Service
 * @author 王念
 * @create 2020-02-18 14:21
 */
public interface UmsMenuService {
    /**
     * 创建后台菜单
     */
    int create(UmsMenu menu);
    /**
     * 修改后台菜单
     */
    int update(Long id,UmsMenu menu);
    /**
     * 根据id获取菜单
     */
    UmsMenu item(Long id);
    /**
     * 根据id删除菜单
     */
    int delete(Long id);
    /**
     * 分页查询菜单
     */
    List<UmsMenu> list(Long parentId,Integer pageNum,Integer pageSize);

    /**
     * 树形结构返回所有菜单列表
     */
    List<UmsMenuNode> treeList();

    /**
     * 修改菜单显示状态
     */
    int updateHidden(Long id, Integer hidden);
}
