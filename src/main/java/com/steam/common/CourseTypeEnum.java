package com.steam.common;

import lombok.Getter;
import org.apache.commons.lang.StringUtils;

/**
 * @author : JOSE 2019/3/25 10:13 PM
 */
@Getter
public enum CourseTypeEnum {
    SCIENCE("0", "科学"),
    TECHNOLOGY("1", "技术"),
    ENGINEERING("2", "工程"),
    ART("3", "艺术"),
    MATH("4", "数学"),
    SYNTHESIZE("5", "综合");

    private String code;
    private String desc;

    CourseTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static String mappingDesc(String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        }

        CourseTypeEnum[] values = CourseTypeEnum.values();
        for (CourseTypeEnum item : values) {
            if (item.code.equals(code)) {
                return item.desc;
            }
        }

        return null;
    }
}
