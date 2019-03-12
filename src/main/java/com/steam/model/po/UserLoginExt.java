package com.steam.model.po;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author : JOSE 2019/3/12 8:05 PM
 */
@Getter
@Setter
public class UserLoginExt extends UserLogin {

    /** 当前时间 */
    private Date nowTime;
}
