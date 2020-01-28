package com.wang.mall.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 王念
 * @create 2020-01-27 20:43
 */
@RestController
@RequestMapping("product")
public class PmsProductController {
    @GetMapping("index")
    public String index() {
        return "8180";
    }
}
