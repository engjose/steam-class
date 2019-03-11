package com.steam.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author : JOSE 2019/3/11 8:43 PM
 */
@Getter
@Setter
@ApiModel("用户返回")
public class UserLoginResponse extends BaseResponse {

    @ApiModelProperty("登录token")
    private String token;
}
