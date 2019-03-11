package com.steam.common;

import lombok.Getter;

/**
 * @author : JOSE 2019/3/11 10:05 PM
 */
@Getter
public enum ErrorEnum {
    USER_NAME_OR_PASSWORD_ERR(40001, "用户名或者密码错误"),
    SYS_ERR(500, "系统忙不过来了");

    private int code;
    private String message;

    ErrorEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
