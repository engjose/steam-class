package com.steam.common;

import lombok.Getter;

/**
 * @author : JOSE 2019/4/22 9:45 PM
 */
@Getter
public enum CollectEnum {
    UN_COLLECT("0", "未收藏"),
    COLLECT("1", "已收藏");

    CollectEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private String code;
    private String desc;
}
