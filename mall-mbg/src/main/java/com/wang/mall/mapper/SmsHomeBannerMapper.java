package com.wang.mall.mapper;

import com.wang.mall.model.SmsHomeBanner;
import com.wang.mall.model.SmsHomeBannerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SmsHomeBannerMapper {
    long countByExample(SmsHomeBannerExample example);

    int deleteByExample(SmsHomeBannerExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SmsHomeBanner record);

    int insertSelective(SmsHomeBanner record);

    List<SmsHomeBanner> selectByExample(SmsHomeBannerExample example);

    SmsHomeBanner selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SmsHomeBanner record, @Param("example") SmsHomeBannerExample example);

    int updateByExample(@Param("record") SmsHomeBanner record, @Param("example") SmsHomeBannerExample example);

    int updateByPrimaryKeySelective(SmsHomeBanner record);

    int updateByPrimaryKey(SmsHomeBanner record);
}