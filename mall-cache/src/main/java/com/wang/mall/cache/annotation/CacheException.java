package com.wang.mall.cache.annotation;

import java.lang.annotation.*;

/**
 * @author 王念
 * @create 2020-03-24 19:40
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CacheException {
}
