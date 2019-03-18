package com.steam.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author : JOSE 2019/3/19 12:10 AM
 */
@ApiModel("积分项")
@Getter
@Setter
public class PointItem {

    @ApiModelProperty("积分来源")
    private String source;

    @ApiModelProperty("积分值")
    private int value;
}
