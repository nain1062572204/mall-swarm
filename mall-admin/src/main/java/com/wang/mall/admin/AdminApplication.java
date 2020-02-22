package com.wang.mall.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


/**
 * @author 王念
 * @create 2020-01-27 20:08
 */

@SpringBootApplication
@EnableFeignClients
public class AdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }
}
