package com.wang.mall.front.dto;

import com.wang.mall.model.SmsFlashPromotionSession;
import lombok.Getter;
import lombok.Setter;


import java.util.List;

/**
 * @author 王念
 * @create 2020-02-20 20:45
 */
public class SmsFlashPromotionWithProduct extends SmsFlashPromotionSession {
    private static final long serialVersionUID = -1820157498780868111L;
    @Getter
    @Setter
    private List<SmsFlashPromotionProduct> products;
}
