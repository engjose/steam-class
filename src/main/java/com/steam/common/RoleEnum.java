package com.steam.common;

import lombok.Getter;

/**
 * @author : JOSE 2019/3/12 8:34 PM
 */
@Getter
public enum  RoleEnum {
    USER("0", "用户"),
    ADMIN("1", "管理员'");

    private String code;
    private String desc;

    RoleEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
