package com.wang.mall.front.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author 王念
 * @create 2020-02-05 23:30
 */
@Configuration
@MapperScan({"com.wang.mall.mapper","com.wang.mall.front.dao"})
public class MybatisConfig {
}
