package com.wang.mall.admin.dto;

import com.wang.mall.model.UmsMenu;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author 王念
 * @create 2020-02-18 14:23
 */
@Getter
@Setter
public class UmsMenuNode extends UmsMenu {
    private static final long serialVersionUID = -2852754164658069357L;
    private List<UmsMenuNode> children;
}
