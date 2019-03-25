package com.steam.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : JOSE 2019/3/25 9:47 PM
 */
@ApiModel("查询课程RESP")
@Getter
@Setter
public class QueryCourseResponse extends BaseResponse {

    @ApiModelProperty("课程列表")
    private List<QueryCourseItem> courseList = new ArrayList<>();
}
