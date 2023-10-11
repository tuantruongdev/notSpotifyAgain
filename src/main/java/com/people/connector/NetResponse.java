package com.people.connector;

public class NetResponse {

    public NetResponse(String message, int status, Object data) {
        this.message = message;
        this.status = status;
        this.data = data;
    }
    public NetResponse(String message, int status,int errorCode, Object data) {
        this.message = message;
        this.status = status;
        this.data = data;
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    private String message;
    private int status;
    private int errorCode = 0;
    Object data;
}
