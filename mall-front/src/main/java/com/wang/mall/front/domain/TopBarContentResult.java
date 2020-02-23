package com.wang.mall.front.domain;

import com.wang.mall.front.dto.PmsProductCategoryWithProduct;
import com.wang.mall.model.PmsProductCategory;
import com.wang.mall.model.SmsHomeAdvertise;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * TopBar内容返回参数
 *
 * @author 王念
 * @create 2020-02-22 17:12
 */
@Data
@Builder
@Getter
@Setter
public class TopBarContentResult implements Serializable {
    private static final long serialVersionUID = -3743554804988466612L;
    List<SmsHomeAdvertise> advertises;
    List<PmsProductCategoryWithProduct> categories;
    List<PmsProductCategory> navProductCategories;
}
