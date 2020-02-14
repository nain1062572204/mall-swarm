package com.wang.mall.dto;

import com.wang.mall.model.SmsFlashPromotionSession;
import lombok.Getter;
import lombok.Setter;

/**
 * 包含商品数量的场次信息
 *
 * @author 王念
 * @create 2020-02-14 15:01
 */
public class SmsFlashPromotionSessionDetail extends SmsFlashPromotionSession {
    private static final long serialVersionUID = 3045288309351629633L;
    @Getter
    @Setter
    private Long productCount;
}
