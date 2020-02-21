package com.wang.mall.admin.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author 王念
 * @create 2020-01-27 20:31
 */
@Configuration
@EnableTransactionManagement
@MapperScan({"com.wang.mall.mapper","com.wang.mall.admin.dao"})
public class MybatisConfig {
}
