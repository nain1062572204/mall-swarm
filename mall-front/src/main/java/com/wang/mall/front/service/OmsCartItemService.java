package com.wang.mall.front.service;

import com.wang.mall.model.OmsCartItem;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 购物车管理Service
 *
 * @author 王念
 * @create 2020-03-02 17:29
 */
public interface OmsCartItemService {
    /**
     * 查询购物车中是否包含该商品，有增加数量，没有增加到购物车
     */
    @Transactional
    int add(OmsCartItem cartItem);

    /**
     * 根据用户id获取购物车列表
     */
    List<OmsCartItem> list(Long memberId);

    /**
     * 修改购物车某个商品数量
     */
    int updateQuantity(Long id, Long memberId, Integer quantity);

    /**
     * 批量删除购物车中的商品
     */
    int delete(Long memberId, List<Long> ids);

    /**
     * 修改购物车商品规格信息
     */
    @Transactional
    int updateAttr(OmsCartItem cartItem);

    /**
     * 清空购物车
     */
    int clear(Long memberId);

    /**
     * 查询指定用户购物车商品数量
     */
    int total();

}
