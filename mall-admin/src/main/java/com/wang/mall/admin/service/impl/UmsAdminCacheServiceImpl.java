package com.wang.mall.admin.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.wang.mall.admin.dao.UmsAdminRoleRelationDao;
import com.wang.mall.admin.service.UmsAdminCacheService;
import com.wang.mall.admin.service.UmsAdminService;
import com.wang.mall.cache.keys.RedisKeys;
import com.wang.mall.cache.service.RedisService;
import com.wang.mall.mapper.UmsAdminRoleRelationMapper;
import com.wang.mall.model.UmsAdmin;
import com.wang.mall.model.UmsAdminRoleRelation;
import com.wang.mall.model.UmsAdminRoleRelationExample;
import com.wang.mall.model.UmsResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 后台用户缓存操作实现类
 *
 * @author 王念
 * @create 2020-03-23 20:55
 */
@Service
@SuppressWarnings("unchecked")
public class UmsAdminCacheServiceImpl implements UmsAdminCacheService {
    @Autowired
    private UmsAdminService adminService;
    @Autowired
    private RedisService redisService;
    @Autowired
    private UmsAdminRoleRelationDao adminRoleRelationDao;
    @Autowired
    private UmsAdminRoleRelationMapper adminRoleRelationMapper;


    @Override
    public void delAdmin(Long adminId) {
        UmsAdmin admin = adminService.getItem(adminId);
        if (admin != null) {
            String key = RedisKeys.DATABASE.getKey() + ":" + RedisKeys.ADMIN.getKey() + ":" + admin.getUsername();
            redisService.del(key);
        }
    }

    @Override
    public void delResourceList(Long adminId) {
        String key = RedisKeys.DATABASE.getKey() + ":" + RedisKeys.RESOURCE_LIST.getKey() + ":" + adminId;
        redisService.del(key);
    }

    @Override
    public void delResourceListByRole(Long roleId) {
        UmsAdminRoleRelationExample example = new UmsAdminRoleRelationExample();
        example.createCriteria().andRoleIdEqualTo(roleId);
        List<UmsAdminRoleRelation> relationList = adminRoleRelationMapper.selectByExample(example);
        if (CollUtil.isNotEmpty(relationList)) {
            String keyPrefix = RedisKeys.DATABASE.getKey() + ":" + RedisKeys.RESOURCE_LIST.getKey() + ":";
            List<String> keys = relationList.stream().map(relation -> keyPrefix + relation.getAdminId()).collect(Collectors.toList());
            redisService.del(keys);
        }
    }

    @Override
    public void delResourceListByRoleIds(List<Long> roleIds) {
        UmsAdminRoleRelationExample example = new UmsAdminRoleRelationExample();
        example.createCriteria().andRoleIdIn(roleIds);
        List<UmsAdminRoleRelation> relationList = adminRoleRelationMapper.selectByExample(example);
        if (CollUtil.isNotEmpty(relationList)) {
            String keyPrefix = RedisKeys.DATABASE.getKey() + ":" + RedisKeys.RESOURCE_LIST.getKey() + ":";
            List<String> keys = relationList.stream().map(relation -> keyPrefix + relation.getAdminId()).collect(Collectors.toList());
            redisService.del(keys);
        }
    }

    @Override
    public void delResourceListByResource(Long resourceId) {
        List<Long> adminIdList = adminRoleRelationDao.getAdminIdList(resourceId);
        if (CollUtil.isNotEmpty(adminIdList)) {
            String keyPrefix = RedisKeys.DATABASE.getKey() + ":" + RedisKeys.RESOURCE_LIST.getKey() + ":";
            List<String> keys = adminIdList.stream().map(adminId -> keyPrefix + adminId).collect(Collectors.toList());
            redisService.del(keys);
        }
    }

    @Override
    public UmsAdmin getAdmin(String username) {
        String key = RedisKeys.DATABASE.getKey() + ":" + RedisKeys.ADMIN.getKey() + ":" + username;
        return (UmsAdmin) redisService.get(key);
    }

    @Override
    public void setAdmin(UmsAdmin admin) {
        String key = RedisKeys.DATABASE.getKey() + ":" + RedisKeys.ADMIN.getKey() + ":" + admin.getUsername();
        redisService.set(key, admin, 60 * 60 * 24);
    }

    @Override
    public List<UmsResource> getResourceList(Long adminId) {
        String key = RedisKeys.DATABASE.getKey() + ":" + RedisKeys.RESOURCE_LIST.getKey() + ":" + adminId;
        return (List<UmsResource>) redisService.get(key);
    }

    @Override
    public void setResourceList(Long adminId, List<UmsResource> resourceList) {
        String key = RedisKeys.DATABASE.getKey() + ":" + RedisKeys.RESOURCE_LIST.getKey() + ":" + adminId;
        redisService.set(key, resourceList, 60 * 60 * 24);
    }
}
