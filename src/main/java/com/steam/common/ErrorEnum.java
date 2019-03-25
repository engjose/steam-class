package com.steam.common;

import lombok.Getter;

/**
 * @author : JOSE 2019/3/11 10:05 PM
 */
@Getter
public enum ErrorEnum {
    REQUEST_PARAM_ERR(40000, "请求参数不正确"),
    USER_NAME_OR_PASSWORD_ERR(40001, "用户名或者密码错误"),
    LOGIN_REPEAT(40002, "不能重复登录"),
    USER_EXISTS(40003, "用户已经存在"),
    TOKEN_UN_EFFECTIVE(40010, "无效的token"),
    USER_ROLE_ERR(40004, "用户角色不正确"),
    SORT_RULE_ERR(40005, "排序规则不正确"),
    SYS_ERR(500, "系统忙不过来了"),
    USER_STATUS_ERR(5000, "用户状态不正确");

    private int code;
    private String message;

    ErrorEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
