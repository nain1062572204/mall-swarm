package com.wang.mall.front.service.impl;

import com.wang.mall.front.domain.MemberDetails;
import com.wang.mall.mapper.UmsMemberMapper;
import com.wang.mall.model.UmsMember;
import com.wang.mall.model.UmsMemberExample;
import com.wang.mall.front.service.UmsMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author 王念
 * @create 2020-02-05 21:09
 */
@Service
public class UmsMemberServiceImpl implements UmsMemberService {
    @Autowired
    private UmsMemberMapper memberMapper;

    @Override
    public UmsMember getByUsername(String username) {
        UmsMemberExample example = new UmsMemberExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<UmsMember> memberList = memberMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(memberList)) {
            return memberList.get(0);
        }
        return null;
    }


    @Override
    public UserDetails loadUserByUsername(String username) {
        UmsMember member = getByUsername(username);
        if (null == member)
            throw new UsernameNotFoundException("用户名或密码错误");
        return new MemberDetails(member);
    }
}
