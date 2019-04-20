package com.steam.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : JOSE 2019/4/20 6:05 PM
 */
@Getter
@Setter
@ApiModel("订单模型")
public class OrderResponse {

    @ApiModelProperty("未付款订单列表")
    private List<OrderItem> draftOrderList = new ArrayList<>();

    @ApiModelProperty("已支付订单列表")
    private List<OrderItem> payOrderList = new ArrayList<>();

    @ApiModelProperty("已取消订单列表")
    private List<OrderItem> cancelOrderList = new ArrayList<>();
}
