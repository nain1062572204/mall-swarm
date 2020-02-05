package com.wang.mall.mapper;

import com.wang.mall.model.SmsHomePromo;
import com.wang.mall.model.SmsHomePromoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SmsHomePromoMapper {
    long countByExample(SmsHomePromoExample example);

    int deleteByExample(SmsHomePromoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SmsHomePromo record);

    int insertSelective(SmsHomePromo record);

    List<SmsHomePromo> selectByExample(SmsHomePromoExample example);

    SmsHomePromo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SmsHomePromo record, @Param("example") SmsHomePromoExample example);

    int updateByExample(@Param("record") SmsHomePromo record, @Param("example") SmsHomePromoExample example);

    int updateByPrimaryKeySelective(SmsHomePromo record);

    int updateByPrimaryKey(SmsHomePromo record);
}