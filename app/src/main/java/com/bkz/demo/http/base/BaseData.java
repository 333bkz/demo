package com.bkz.demo.http.base;

import java.io.Serializable;

public class BaseData<T> implements Serializable {

    public T data;
    public int code;
    public String message;

    public boolean isSuccess() {
        return code == 0;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "BaseData{" +
                "data='" + (data == null ? "" : data.toString()) + '\'' +
                ", code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
