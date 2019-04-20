package com.steam.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * @author : JOSE 2019/4/20 5:43 PM
 */
@Getter
@Setter
@ApiModel("支付REQ")
public class PayOrderRequest extends BaseRequest {

    @NotBlank(message = "订单ID不能为空")
    @ApiModelProperty("订单ID")
    private String orderId;
}
