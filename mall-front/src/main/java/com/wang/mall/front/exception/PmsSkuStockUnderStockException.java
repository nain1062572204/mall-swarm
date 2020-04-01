package com.wang.mall.front.exception;

/**
 * 商品库存不足异常
 *
 * @author 王念
 * @create 2020-03-31 19:00
 */
public class PmsSkuStockUnderStockException extends RuntimeException {
    private static final long serialVersionUID = -8861535306208055920L;
    public PmsSkuStockUnderStockException(){
        super();
    }

    //用详细信息指定一个异常
    public PmsSkuStockUnderStockException(String message){
        super(message);
    }

}
