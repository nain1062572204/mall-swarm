package com.wang.mall.dao;

import com.wang.mall.model.PmsProductVerifyRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品审核日志自定义dao
 *
 * @author 王念
 * @create 2020-02-11 17:33
 */
public interface PmsProductVerifyRecordDao {
    int insertList(@Param("list") List<PmsProductVerifyRecord> list);
}
