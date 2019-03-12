package com.steam.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * @author : JOSE 2019/3/11 8:30 PM
 */
@Getter
@Setter
@ApiModel("用户登录REQ")
public class UserLoginRequest {

    @Length(min = 3, max = 10, message = "昵称必须在3-10之间")
    @ApiModelProperty("用户名")
    private String nickName;

    @Length(min = 6, max = 8, message = "密码必须在6-8位之间")
    @ApiModelProperty("密码 MD5加密")
    private String password;
}
