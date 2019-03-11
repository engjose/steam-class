package com.steam.model.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * @author : JOSE 2019/3/11 10:28 PM
 */
@Getter
@Setter
public class BaseResponse {

    private int code = 0;
    private String message = "SUCCESS";

}
