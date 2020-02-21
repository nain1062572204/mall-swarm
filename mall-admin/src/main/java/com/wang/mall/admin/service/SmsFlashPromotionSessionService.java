package com.wang.mall.admin.service;

import com.wang.mall.admin.dto.SmsFlashPromotionSessionDetail;
import com.wang.mall.model.SmsFlashPromotionSession;

import java.util.List;

/**
 * 闪购场次Service
 *
 * @author 王念
 * @create 2020-02-14 14:52
 */
public interface SmsFlashPromotionSessionService {
    /**
     * 添加场次
     */
    int create(SmsFlashPromotionSession promotionSession);

    /**
     * 修改场次
     */
    int update(Long id, SmsFlashPromotionSession promotionSession);

    /**
     * 修改场次启用状态
     */
    int updateStatus(Long id, Integer status);

    /**
     * 删除场次
     */
    int delete(Long id);

    /**
     * 获取详情
     */
    SmsFlashPromotionSession item(Long id);

    /**
     * 根据启用状态获取场次列表
     */
    List<SmsFlashPromotionSession> list(Integer pageNum, Integer pageSize);

    /**
     * 获取全部可选场次及其商品数量
     */
    List<SmsFlashPromotionSessionDetail> selectList();
}
