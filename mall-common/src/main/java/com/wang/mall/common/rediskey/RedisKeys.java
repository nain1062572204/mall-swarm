package com.wang.mall.common.rediskey;

/**
 * @author 王念
 * @create 2020-02-22 13:59
 * category: "home:advertise" #分类信息
 * homeAdvertise: "home:category" #轮播图信息
 * flashPromotion: "home:flash" #闪购信息
 * homeProduct: "home:product" #首页推荐商品
 */
public enum RedisKeys {
    HOME_ADVERTISE("home:advertise"),
    CATEGORY("home:category"),
    FLASH_PROMOTION("home:flash"),
    HOME_PRODUCT("home:product"),
    NAV_CATEGORY("nav:category");

    private String key;

    private RedisKeys(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

}
