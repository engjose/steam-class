package com.steam.common;

import lombok.Getter;

/**
 * @author : JOSE 2019/3/12 7:57 PM
 */
@Getter
public enum  UserStatusEnum {
    UN_ACTIVE("0", "冻结状态"),
    ACTIVE("1", "激活状态");

    private String code;
    private String desc;

    UserStatusEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
