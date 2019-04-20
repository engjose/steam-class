package com.steam.web;

import com.steam.model.vo.BaseResponse;
import com.steam.model.vo.PayOrderRequest;
import com.steam.model.vo.PlaceOrderRequest;
import com.steam.model.vo.PlaceOrderResponse;
import com.steam.service.IOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author : JOSE 2019/4/20 4:28 PM
 */
@Api(tags = "【订单】服务")
@RefreshScope
@RestController
@RequestMapping(value = "/order", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class OrderController {

    @Autowired
    private IOrderService  iOrderService;

    @ApiOperation("下单")
    @PostMapping(value = "/place")
    public PlaceOrderResponse placeOrder(@Valid @RequestBody PlaceOrderRequest request) {
        return iOrderService.placeOrder(request);
    }

    @ApiOperation("支付")
    @PostMapping(value = "/pay")
    public BaseResponse payOrder(@Valid @RequestBody PayOrderRequest request) {
        iOrderService.pay(request.getOrderId(), request.getToken());
        return new BaseResponse();
    }

    @ApiOperation("取消订单")
    @PostMapping(value = "/cancel")
    public BaseResponse cancelOrder(@Valid @RequestBody PayOrderRequest request) {
        iOrderService.cancelOrder(request.getOrderId(), request.getToken());
        return new BaseResponse();
    }
}
