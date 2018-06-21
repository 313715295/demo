package com.zwq.commons.exception;

/**
 * created by zwq on 2018/6/20
 */
public class StockOutException extends RuntimeException {

    public StockOutException(String message) {
        super(message);
    }


    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
