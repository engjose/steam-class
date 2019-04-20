package com.steam.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @author : JOSE 2019/4/20 6:06 PM
 */
@Getter
@Setter
@ApiModel("订单元数据")
public class OrderItem {

    @ApiModelProperty("订单id")
    private String orderId;

    @ApiModelProperty("用户ID")
    private String userId;

    @ApiModelProperty("课程ID")
    private String courseId;

    @ApiModelProperty("课程名称")
    private String courseName;

    @ApiModelProperty("价格类型")
    private String priceType;

    @ApiModelProperty("价格类型描述")
    private String priceTypeDesc;

    @ApiModelProperty("订单状态")
    private String status;

    @ApiModelProperty("订单状态描述")
    private String statusDesc;

    @ApiModelProperty("价格")
    private BigDecimal price;

    @ApiModelProperty("课程类型")
    private String courseType;

    @ApiModelProperty("课程类型描述")
    private String courseTypeDesc;

    @ApiModelProperty("商户")
    private String merchantName;

    @ApiModelProperty("图片")
    private String coursePic;

    @ApiModelProperty("创建时间")
    private String createTime;
}
