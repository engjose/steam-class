package com.steam.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author : JOSE 2019/3/18 10:22 PM
 */
@Getter
@Setter
@ApiModel("用户信息")
public class UserResponse {

    @ApiModelProperty("昵称")
    private String nickName;

    @ApiModelProperty("注册时间 yyyy-MM-dd")
    private String registerDate;

    @ApiModelProperty("头像")
    private String headPic;
}
