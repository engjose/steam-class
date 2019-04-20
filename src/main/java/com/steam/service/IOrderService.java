package com.steam.service;

import com.steam.model.po.Order;
import com.steam.model.vo.PlaceOrderRequest;
import com.steam.model.vo.PlaceOrderResponse;

import java.util.List;

/**
 * @author : JOSE 2019/4/20 4:28 PM
 */
public interface IOrderService {

    /**
     * 下单接口
     *
     * @param request 下单请求
     */
    PlaceOrderResponse placeOrder(PlaceOrderRequest request);

    /**
     * 支付接口
     *
     * @param orderId order id
     */
    void pay(String orderId, String token);

    /**
     * 取消订单
     *
     * @param orderId order id
     */
    void cancelOrder(String orderId, String token);

    /**
     * select by uid and course id
     *
     * @param uid ui
     * @param courseId course id
     * @return order
     */
    Order selectByUidAndCourseId(String uid, String courseId);

    /**
     * select list
     *
     * @param uid uid
     * @return order list
     */
    List<Order> selectList(String uid);
}
