package com.wang.mall.admin.dao;

import org.apache.ibatis.annotations.Select;

/**
 * @author 王念
 * @create 2020-04-19 21:37
 */
public interface SmsHomeAdvertiseDao {
    /**
     * 获取即将到期广告数量(还有一天过期)
     */
    @Select("SELECT count(*) FROM sms_home_advertise WHERE DATEDIFF(end_time,NOW()) <1")
    Integer getExpireAdvertise();
}
