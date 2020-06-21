package com.unogwudan.exception;


public class AbstractException extends RuntimeException {

    public AbstractException(String statusCode, String message) {

        super(message);
        this.setCode(statusCode);
    }


    private String code;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
