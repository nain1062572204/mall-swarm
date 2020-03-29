package com.wang.mall.cache.keys;

/**
 * @author 王念
 * @create 2020-02-22 13:59
 * category: "home:advertise" #分类信息
 * homeAdvertise: "home:category" #轮播图信息
 * flashPromotion: "home:flash" #闪购信息
 * homeProduct: "home:product" #首页推荐商品
 */
public enum RedisKeys {
    OMS_CART_ITEM("mall::oms:cartItem:"),
    HOME_ADVERTISE("mall::home:advertise"),
    CATEGORY("mall::home:category"),
    FLASH_PROMOTION("mall::home:flash"),
    HOME_PRODUCT("mall::home:product"),
    NAV_CATEGORY("mall::nav:category"),
    DATABASE("mall"),
    ADMIN("usm:admin"),
    RESOURCE_LIST("ums:resourceList"),
    MEMBER("mall:ums:member"),
    PMS_PRODUCT("mall:pms:product"),
    SEARCH_ADVERTISE("mall:home:searchAdvertise");

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
