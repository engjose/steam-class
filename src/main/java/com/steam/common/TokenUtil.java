package com.steam.common;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @author : JOSE 2019/3/11 10:12 PM
 */
public class TokenUtil {

    public static String getToken(String head) {
        return head + "_" + new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + getFixNum(4);
    }

    private static String getFixNum(int strLength) {

        Random rm = new Random();

        // 获得随机数
        double pross = (1 + rm.nextDouble()) * Math.pow(10, strLength);

        // 将获得的获得随机数转化为字符串
        String fixLenthString = String.valueOf(pross);

        // 返回固定的长度的随机数
        return fixLenthString.substring(1, strLength + 1);
    }
}
