package com.wang.mall.search.dao;

import com.wang.mall.search.domain.EsProduct;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 王念
 * @create 2020-01-28 19:38
 */
@Repository
public interface EsProductDao {
    List<EsProduct> getAllEsProductList(@Param("id") Long id);
}
