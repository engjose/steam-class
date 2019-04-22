package com.steam.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * @author : JOSE 2019/4/22 10:54 PM
 */
@Getter
@Setter
@ApiModel("收藏课程")
public class CollectRequest extends BaseRequest {

    @NotBlank(message = "课程ID不能我空")
    @ApiModelProperty("课程ID")
    private String courseId;
}
