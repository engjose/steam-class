package com.steam.common;

import lombok.Getter;

/**
 * @author : JOSE 2019/4/20 4:49 PM
 */
@Getter
public enum PriceTypeEnum {
    FREE("0", "免费"),
    CHARGE("1", "收费");

    private String code;
    private String desc;

    PriceTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static String mappingDesc(String code) {
        for (PriceTypeEnum item : PriceTypeEnum.values()) {
            if (item.code.equals(code)) {
                return item.desc;
            }
        }

        return null;
    }
}
