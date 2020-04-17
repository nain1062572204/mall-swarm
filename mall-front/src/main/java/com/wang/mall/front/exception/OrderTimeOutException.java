package com.wang.mall.front.exception;

public class OrderTimeOutException extends RuntimeException {
    private static final long serialVersionUID = 643102442974085806L;

    public OrderTimeOutException() {
        super();
    }

    public OrderTimeOutException(String message) {
        super(message);
    }
}
