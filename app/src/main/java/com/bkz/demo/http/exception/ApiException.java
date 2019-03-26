package com.bkz.demo.http.exception;

public final class ApiException extends Throwable {
    private int code;//错误码
    private String message;//错误信息

    public ApiException(Throwable throwable, int code) {
        super(throwable);
        this.code = code;
    }

    public ApiException(int code, String msg) {
        this.code = code;
        this.message = msg;
    }

    public static ApiException handleException(int code, String message) {
        return new ApiException(code, message);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}