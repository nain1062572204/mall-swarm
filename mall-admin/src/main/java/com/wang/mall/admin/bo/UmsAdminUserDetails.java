package com.wang.mall.admin.bo;


import com.wang.mall.model.UmsAdmin;
import com.wang.mall.model.UmsResource;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 王念
 * @create 2020-02-07 17:11
 */
@NoArgsConstructor
@AllArgsConstructor
public class UmsAdminUserDetails implements UserDetails {
    private static final long serialVersionUID = 6811818070079863639L;

    private UmsAdmin umsAdmin;
    private List<UmsResource> resources;


    public Collection<? extends GrantedAuthority> getAuthorities() {
        //返回当前用户的资源
        return resources.stream()
                .map(role -> new SimpleGrantedAuthority(role.getId() + ":" + role.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return umsAdmin.getPassword();
    }

    @Override
    public String getUsername() {
        return umsAdmin.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return umsAdmin.getStatus().equals(1);
    }
}
