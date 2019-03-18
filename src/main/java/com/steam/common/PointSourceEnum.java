package com.steam.common;

import lombok.Getter;

/**
 * @author : JOSE 2019/3/19 12:12 AM
 */
@Getter
public enum PointSourceEnum {
    REGISTRY("0", "新户注册"),
    ACTIVITY("1", "活动奖励");

    private String code;
    private String desc;

    PointSourceEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static String mappingDesc(String code) {
        for (PointSourceEnum item : PointSourceEnum.values()) {
            if (item.code.equals(code)) {
                return item.desc;
            }
        }

        return null;
    }
}
