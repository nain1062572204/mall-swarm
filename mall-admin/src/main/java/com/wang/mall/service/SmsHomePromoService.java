package com.wang.mall.service;

import com.wang.mall.model.SmsHomePromo;

import java.util.List;

/**
 * 首页Promo Service
 *
 * @author 王念
 * @create 2020-02-14 13:55
 */
public interface SmsHomePromoService {
    /**
     * 添加促销
     */
    int create(SmsHomePromo promo);

    /**
     * 批量删除促销
     */
    int delete(List<Long> ids);

    /**
     * 修改上下线状态
     */
    int updateStatus(Long id, Integer status);

    /**
     * 获取促销详情
     */
    SmsHomePromo item(Long id);

    /**
     * 更新促销
     */
    int update(Long id, SmsHomePromo promo);

    /**
     * 分页查询促销
     */
    List<SmsHomePromo> list(String endTime, Integer pageSize, Integer pageNum);

}
