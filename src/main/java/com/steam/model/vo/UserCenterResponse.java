package com.steam.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author : JOSE 2019/3/18 10:21 PM
 */
@Getter
@Setter
@ApiModel("用户中心")
public class UserCenterResponse extends BaseResponse {

    @ApiModelProperty("用户基本信息")
    private UserResponse userInfo;

    @ApiModelProperty("积分信息")
    private PointResponse point;
}
