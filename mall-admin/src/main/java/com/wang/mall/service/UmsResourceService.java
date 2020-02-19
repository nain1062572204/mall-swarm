package com.wang.mall.service;

import com.wang.mall.model.UmsResource;

import java.util.List;

/**后台资源管理Service
 * @author 王念
 * @create 2020-02-18 15:47
 */
public interface UmsResourceService {
    int create(UmsResource resource);

    int update(Long id, UmsResource resource);

    UmsResource getItem(Long id);

    int delete(Long id);

    List<UmsResource> list(Long categoryId, String nameKeyword, String urlKeyword, Integer pageSize, Integer pageNum);

    List<UmsResource> listAll();
}
