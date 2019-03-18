package com.steam.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author : JOSE 2019/3/18 10:27 PM
 */
@Getter
@Setter
@ApiModel("积分模型")
public class PointResponse {

    @ApiModelProperty("积分总数")
    private int totalPoint;

    @ApiModelProperty("积分明细")
    private List<PointItem> pointList;

}
