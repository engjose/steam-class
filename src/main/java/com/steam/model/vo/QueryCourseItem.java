package com.steam.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @author : JOSE 2019/3/25 10:05 PM
 */
@Getter
@Setter
@ApiModel("课程模型")
public class QueryCourseItem {

    @ApiModelProperty("课程ID")
    private String courseId;

    @ApiModelProperty("课程名称")
    private String courseName;

    @ApiModelProperty("课程简介信息")
    private String courseInfo;

    @ApiModelProperty("课程封面图片")
    private String coursePic;

    @ApiModelProperty("课程video")
    private String videoUrl;

    @ApiModelProperty("课程类型")
    private String courseType;

    @ApiModelProperty("课程类型描述")
    private String courseTypeDesc;

    @ApiModelProperty("价格类型: 0-免费 1-收费")
    private String priceType;

    @ApiModelProperty("价格类型: 0-免费 1-收费")
    private String priceTypeDesc;

    @ApiModelProperty("课程价格")
    private BigDecimal price;

    @ApiModelProperty("教师名称")
    private String teacherName;

    @ApiModelProperty("教师简介信息")
    private String teacherInfo;

    @ApiModelProperty("商户名称")
    private String merchantName;

    @ApiModelProperty("商户简介信息")
    private String merchantInfo;

    @ApiModelProperty("是否购买")
    private Boolean isBuy;
}
