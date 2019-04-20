package com.steam.common;

import lombok.Getter;

/**
 * @author : JOSE 2019/4/20 4:53 PM
 */
@Getter
public enum OrderStatusEnum {
    DRAFT("0", "待付款"),
    PAYED("1", "已完成"),
    CANCEL("2", "已取消");

    private String code;
    private String desc;

    OrderStatusEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static String mappingDesc(String code) {
        for (OrderStatusEnum item : OrderStatusEnum.values()) {
            if (item.code.equals(code)) {
                return item.desc;
            }
        }

        return null;
    }
}
