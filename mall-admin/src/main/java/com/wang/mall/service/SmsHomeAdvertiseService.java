package com.wang.mall.service;

import com.wang.mall.model.SmsHomeAdvertise;

import java.util.List;

/**
 * 首页广告管理Service
 *
 * @author 王念
 * @create 2020-02-13 23:03
 */
public interface SmsHomeAdvertiseService {
    /**
     * 添加广告
     */
    int create(SmsHomeAdvertise advertise);

    /**
     * 批量删除广告
     */
    int delete(List<Long> ids);

    /**
     * 修改上线、下线状态
     */
    int updateStatus(Long id, Integer status);

    /**
     * 获取广告详情
     */
    SmsHomeAdvertise item(Long id);

    /**
     * 更新广告
     */
    int update(Long id, SmsHomeAdvertise advertise);

    /**
     * 分页查询广告
     */

    List<SmsHomeAdvertise> list(Integer type, String endTime, Integer pageSize, Integer pageNum);
}
