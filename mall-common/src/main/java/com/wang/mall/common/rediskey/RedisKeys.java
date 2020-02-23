package com.wang.mall.common.rediskey;

/**
 * @author 王念
 * @create 2020-02-22 13:59
 * category: "CATEGORY" #分类信息
 * homeAdvertise: "HOME_ADVERTISE" #轮播图信息
 * flashPromotion: "FLASH_PROMOTION" #闪购信息
 * homeProduct: "HOME_PRODUCT" #首页推荐商品
 */
public enum RedisKeys {
    HOME_ADVERTISE("HOME_ADVERTISE"),
    CATEGORY("CATEGORY"),
    FLASH_PROMOTION("FLASH_PROMOTION"),
    HOME_PRODUCT("HOME_PRODUCT"),
    NAV_CATEGORY("NAV_CATEGORY")
    ;

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
