package com.wang.mall.admin.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author 王念
 * @create 2020-02-11 17:17
 */
@Getter
@Setter
public class PmsProductResult extends PmsProductParam {
    private static final long serialVersionUID = -5936183314676110014L;
    //商品所选分类的父id
    private Long cateParentId;

}
