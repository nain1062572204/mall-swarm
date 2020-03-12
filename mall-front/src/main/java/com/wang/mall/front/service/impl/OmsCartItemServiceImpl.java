package com.wang.mall.front.service.impl;

import com.wang.mall.front.dao.PmsProductDao;
import com.wang.mall.front.service.OmsCartItemService;
import com.wang.mall.front.service.UmsMemberService;
import com.wang.mall.mapper.OmsCartItemMapper;
import com.wang.mall.model.OmsCartItem;
import com.wang.mall.model.OmsCartItemExample;
import com.wang.mall.model.OmsOrderItemExample;
import com.wang.mall.model.UmsMember;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * 购物车列表Service实现类
 *
 * @author 王念
 * @create 2020-03-05 22:47
 */
@Service
@Slf4j
public class OmsCartItemServiceImpl implements OmsCartItemService {
    @Autowired
    private OmsCartItemMapper cartItemMapper;
    @Autowired
    private PmsProductDao productDao;
    @Autowired
    private UmsMemberService memberService;

    @Override
    public int add(OmsCartItem cartItem) {
        int count;
        UmsMember currentMember = memberService.getCurrentMember();
        cartItem.setMemberId(currentMember.getId());
        cartItem.setDeleteStatus(0);
        OmsCartItem existCartItem = getCartItem(cartItem);
        if (existCartItem == null) {
            //不存在插入
            cartItem.setCreateTime(new Date());
            count = cartItemMapper.insert(cartItem);
            return count;
        }
        existCartItem.setQuantity(existCartItem.getQuantity() + cartItem.getQuantity());
        count = cartItemMapper.updateByPrimaryKey(existCartItem);
        return count;
    }

    private OmsCartItem getCartItem(OmsCartItem cartItem) {
        OmsCartItemExample example = new OmsCartItemExample();
        OmsCartItemExample.Criteria criteria = example.createCriteria()
                .andMemberIdEqualTo(cartItem.getMemberId())
                .andProductIdEqualTo(cartItem.getProductId())
                .andDeleteStatusEqualTo(0);
        if (!StringUtils.isEmpty(cartItem.getProductSkuId()))
            criteria.andProductSkuIdEqualTo(cartItem.getProductSkuId());
        List<OmsCartItem> cartItemList = cartItemMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(cartItemList)) {
            return cartItemList.get(0);
        }
        return null;

    }

    @Override
    public List<OmsCartItem> list(Long memberId) {
        OmsCartItemExample example = new OmsCartItemExample();
        example.createCriteria().andDeleteStatusEqualTo(0)
                .andMemberIdEqualTo(memberId);
        return cartItemMapper.selectByExample(example);
    }

    @Override
    public int updateQuantity(Long id, Long memberId, Integer quantity) {
        OmsCartItem cartItem = OmsCartItem.builder()
                .memberId(memberId)
                .quantity(quantity)
                .id(id)
                .build();
        OmsCartItemExample example = new OmsCartItemExample();
        example.createCriteria().andDeleteStatusEqualTo(0)
                .andIdEqualTo(id)
                .andMemberIdEqualTo(memberId);
        return cartItemMapper.updateByExampleSelective(cartItem, example);
    }

    @Override
    public int delete(Long memberId, List<Long> ids) {
        OmsCartItem record = OmsCartItem.builder()
                .deleteStatus(1).build();
        OmsCartItemExample example = new OmsCartItemExample();
        example.createCriteria().andMemberIdEqualTo(memberId).andIdIn(ids);
        return cartItemMapper.updateByExampleSelective(record, example);
    }

    @Override
    public int updateAttr(OmsCartItem cartItem) {
        //删除原有信息
        OmsCartItem updateCart = OmsCartItem.builder()
                .id(cartItem.getId())
                .deleteStatus(1)
                .build();
        cartItemMapper.updateByPrimaryKeySelective(updateCart);
        cartItem.setId(null);
        add(cartItem);
        return 1;
    }

    @Override
    public int clear(Long memberId) {
        OmsCartItem record = new OmsCartItem();
        record.setDeleteStatus(1);
        OmsCartItemExample example = new OmsCartItemExample();
        example.createCriteria().andMemberIdEqualTo(memberId);
        return cartItemMapper.updateByExampleSelective(record, example);
    }

    @Override
    public int total() {
        UmsMember currentMember = memberService.getCurrentMember();
        OmsCartItemExample example = new OmsCartItemExample();
        example.createCriteria().andMemberIdEqualTo(currentMember.getId())
                .andDeleteStatusEqualTo(0);
        return cartItemMapper.selectByExample(example).size();

    }
}
