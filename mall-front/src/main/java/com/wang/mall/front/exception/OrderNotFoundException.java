package com.wang.mall.front.exception;

public class OrderNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1394853561762799320L;

    public OrderNotFoundException() {
        super();
    }

    public OrderNotFoundException(String message) {
        super(message);
    }
}
