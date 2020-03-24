package com.wang.mall.front.service.impl;

import com.wang.mall.front.domain.MemberDetails;
import com.wang.mall.front.service.FrontCacheService;
import com.wang.mall.mapper.UmsMemberMapper;
import com.wang.mall.model.UmsMember;
import com.wang.mall.model.UmsMemberExample;
import com.wang.mall.front.service.UmsMemberService;
import com.wang.mall.security.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private FrontCacheService frontCacheService;

    @Override
    public UmsMember getByUsername(String username) {
        //先从缓存中获取用户信息
        UmsMember member = frontCacheService.getMember(username);
        if (member != null) {
            return member;
        }
        UmsMemberExample example = new UmsMemberExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<UmsMember> memberList = memberMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(memberList)) {
            member = memberList.get(0);
            frontCacheService.setMember(member);
        }
        return member;
    }


    @Override
    public UserDetails loadUserByUsername(String username) {
        UmsMember member = getByUsername(username);
        if (null == member)
            throw new UsernameNotFoundException("用户名或密码错误");
        return new MemberDetails(member);
    }

    @Override
    public String login(String username, String password) {
        String token;
        UserDetails userDetails = loadUserByUsername(username);
        if (!passwordEncoder.matches(password, userDetails.getPassword()))
            throw new BadCredentialsException("用户名或密码不正确");
        UsernamePasswordAuthenticationToken
                authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        token = jwtTokenUtil.generateToken(userDetails);
        return token;
    }

    @Override
    public UmsMember getCurrentMember() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication auth = context.getAuthentication();
        MemberDetails memberDetails = (MemberDetails) auth.getPrincipal();
        return memberDetails.getUmsMember();
    }
}
