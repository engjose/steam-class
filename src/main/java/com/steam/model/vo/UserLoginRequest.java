package com.steam.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * @author : JOSE 2019/3/11 8:30 PM
 */
@Getter
@Setter
@ApiModel("用户登录REQ")
public class UserLoginRequest {

    @NotBlank(message = "用户名不能为空")
    @ApiModelProperty("用户名")
    private String nickName;

    @NotBlank(message = "密码不能为空")
    @ApiModelProperty("密码 MD5加密")
    private String password;
}
