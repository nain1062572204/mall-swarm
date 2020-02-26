package com.wang.mall.search.domain;

import com.wang.mall.common.api.CommonPage;
import lombok.*;
import org.springframework.data.domain.Page;

import java.io.Serializable;

/**
 * 搜索结果返回内容
 *
 * @author 王念
 * @create 2020-02-26 16:02
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class SearchResult implements Serializable {
    private static final long serialVersionUID = -3987034798971854747L;
    CommonPage<EsProduct> products;
    EsProductRelatedInfo productRelatedInfo;
}
