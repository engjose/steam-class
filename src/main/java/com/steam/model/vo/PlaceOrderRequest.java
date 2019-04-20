package com.steam.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author : JOSE 2019/4/20 4:34 PM
 */
@Getter
@Setter
@ApiModel("下单REQ")
public class PlaceOrderRequest extends BaseRequest {

    @NotBlank(message = "课程ID不能为空")
    @ApiModelProperty("课程id")
    private String courseId;

    @NotNull(message = "课程价格")
    @ApiModelProperty("课程价格")
    private BigDecimal price;
}
