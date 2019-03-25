package com.steam.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author : JOSE 2019/3/25 9:45 PM
 */
@ApiModel("查询课程REQ")
@Getter
@Setter
public class QueryCourseRequest extends BaseRequest {

    @ApiModelProperty("课程名称: 0-科学 1-技术 2-工程 3-艺术 4-数学 5-综合")
    private String courseType;

    @ApiModelProperty("按照课程名称模糊匹配")
    private String courseNameMatch;

    @ApiModelProperty("按照价格排序规则: 0表示升序, 1表示降序")
    private String priceSort;
}
