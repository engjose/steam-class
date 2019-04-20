package com.steam.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author : JOSE 2019/4/20 5:16 PM
 */
@Getter
@Setter
@ApiModel("下单返回")
public class PlaceOrderResponse extends BaseResponse {

    @ApiModelProperty("订单ID")
    private String orderId;
}
