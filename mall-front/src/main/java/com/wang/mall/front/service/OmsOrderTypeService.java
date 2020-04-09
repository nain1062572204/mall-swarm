package com.wang.mall.front.service;

import com.wang.mall.front.dto.OmsOrderWithItemDTO;

import java.util.List;

/**
 * @author 王念
 * @create 2020-04-08 21:23
 */
public interface OmsOrderTypeService {
    List<OmsOrderWithItemDTO> list(Long memberId);
}
