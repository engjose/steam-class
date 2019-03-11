package com.steam.common;

/**
 * @author : JOSE 2019/3/11 10:01 PM
 */
public class SteamException extends RuntimeException {

    /** 返回code */
    private int code;

    /** 返回信息 */
    private String message;

    public SteamException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
