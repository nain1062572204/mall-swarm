package com.wang.mall.front.exception;

/**
 * 商品库存不存在异常
 *
 * @author 王念
 * @create 2020-03-31 19:36
 */
public class PmsSkuStockNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 279873222899195982L;

    public PmsSkuStockNotFoundException() {
        super();
    }

    public PmsSkuStockNotFoundException(String message) {
        super(message);
    }
}
