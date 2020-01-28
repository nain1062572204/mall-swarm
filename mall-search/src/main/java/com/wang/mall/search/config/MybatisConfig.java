package com.wang.mall.search.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author 王念
 * @create 2020-01-28 19:35
 */
@Configuration
@MapperScan({"com.wang.mall.mapper","com.wang.mall.search.dao"})
public class MybatisConfig {
}
