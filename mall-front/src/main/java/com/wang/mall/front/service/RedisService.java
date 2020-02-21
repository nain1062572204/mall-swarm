package com.wang.mall.front.service;


/**
 * @author 王念
 * @create 2020-02-09 19:56
 * Redis操作Service
 */

public interface RedisService {
    /**
     * 从Redis中获取数据
     */
    String get(String key);

    /**
     * 向redis中存储数据
     */
    void set(String key, String value);

    /**
     * 设置超时时间
     */
    boolean expire(String key, long expire);

    /**
     * 删除数据
     */
    void delete(String key);

    /**
     * 自增操作
     */
    Long increment(String key, long delta);

    /**
     * 清除所有数据
     */
    void flushAll();
}
