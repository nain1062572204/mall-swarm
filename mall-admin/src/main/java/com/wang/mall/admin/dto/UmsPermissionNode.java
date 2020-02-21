package com.wang.mall.admin.dto;

import com.wang.mall.model.UmsPermission;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author 王念
 * @create 2020-02-18 14:53
 */
@Getter
@Setter
public class UmsPermissionNode extends UmsPermission {
    private static final long serialVersionUID = 4056042799154971671L;
    private List<UmsPermissionNode> children;
}
