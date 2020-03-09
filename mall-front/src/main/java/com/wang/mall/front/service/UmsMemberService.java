package com.wang.mall.front.service;

import com.wang.mall.model.UmsMember;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author 王念
 * @create 2020-02-05 21:08
 */
public interface UmsMemberService {
    UmsMember getByUsername(String username);

    UserDetails loadUserByUsername(String username);

    String login(String username, String password);

    UmsMember getCurrentMember();
}
