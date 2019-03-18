package com.steam.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author : JOSE 2019/3/18 10:39 PM
 */
@Getter
@Setter
@ApiModel("请求基础父类")
public class BaseRequest {

    @ApiModelProperty("请求登录token")
    private String token;
}
