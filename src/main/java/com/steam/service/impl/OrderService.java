package com.steam.service.impl;

import com.steam.common.*;
import com.steam.dao.CourseOrderMapper;
import com.steam.model.po.Course;
import com.steam.model.po.CourseOrder;
import com.steam.model.po.OrderExt;
import com.steam.model.vo.PlaceOrderRequest;
import com.steam.model.vo.PlaceOrderResponse;
import com.steam.service.ICourseService;
import com.steam.service.IOrderService;
import com.steam.service.IPointService;
import com.steam.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author : JOSE 2019/4/20 4:38 PM
 */
@Service
public class OrderService implements IOrderService {

    @Autowired
    private IUserService iUserService;

    @Autowired
    private ICourseService iCourseService;

    @Autowired
    private IPointService iPointService;

    @Autowired
    private CourseOrderMapper courseOrderMapper;

    @Transactional
    @Override
    public PlaceOrderResponse placeOrder(PlaceOrderRequest request) {
        // check token
        String uid = iUserService.checkToken(request.getToken());

        // select course info
        Course course = iCourseService.selectByCourseId(request.getCourseId());
        if (PriceTypeEnum.CHARGE.getCode().equals(course.getPriceType()) && course.getPrice().compareTo(request.getPrice()) != 0) {
            throw new SteamException(ErrorEnum.COURSE_PRICE_ERR.getCode(), ErrorEnum.COURSE_PRICE_ERR.getMessage());
        }

        // place order
        String status = PriceTypeEnum.CHARGE.getCode().equals(course.getPriceType()) ? OrderStatusEnum.DRAFT.getCode() : OrderStatusEnum.PAYED.getCode();
        String orderId = TokenUtil.getToken("ORDER");
        CourseOrder record = new CourseOrder();
        record.setUserId(uid);
        record.setOrderId(orderId);
        record.setCourseId(course.getCourseId());
        record.setCourseName(course.getCourseName());
        record.setPriceType(course.getPriceType());
        record.setStatus(status);
        record.setPrice(course.getPrice());
        courseOrderMapper.insertSelective(record);

        // build return
        PlaceOrderResponse response = new PlaceOrderResponse();
        response.setOrderId(orderId);
        return response;
    }

    @Override
    public void pay(String orderId, String token) {
        // check token
        iUserService.checkToken(token);

        // check order
        CourseOrder order = courseOrderMapper.selectByOrderId(orderId);
        check(order);

        // 防止免费课程支付
        if (!PriceTypeEnum.CHARGE.getCode().equals(order.getPriceType())) {
            throw new SteamException(ErrorEnum.ORDER_PAY_FREE_ERR.getCode(), ErrorEnum.ORDER_PAY_FREE_ERR.getMessage());
        }

        // pay order
        CourseOrder record = new CourseOrder();
        record.setId(order.getId());
        record.setStatus(OrderStatusEnum.PAYED.getCode());
        courseOrderMapper.updateByPrimaryKeySelective(record);

        // send point
        iPointService.addPoint(order.getUserId(), PointSourceEnum.BUY_COURSE.getCode(), 10);
    }

    @Override
    public void cancelOrder(String orderId, String token) {
        // check token
        iUserService.checkToken(token);

        // check order
        CourseOrder order = courseOrderMapper.selectByOrderId(orderId);
        check(order);

        // cancel order
        CourseOrder record = new CourseOrder();
        record.setId(order.getId());
        record.setStatus(OrderStatusEnum.CANCEL.getCode());
        courseOrderMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public CourseOrder selectByUidAndCourseId(String uid, String courseId) {
        OrderExt criteria = new OrderExt();
        criteria.setCourseId(courseId);
        criteria.setUserId(uid);
        List<CourseOrder> orderList = courseOrderMapper.selectList(criteria);

        return CollectionUtils.isEmpty(orderList) ? null : orderList.get(0);
    }

    @Override
    public List<CourseOrder> selectList(String uid) {
        OrderExt criteria = new OrderExt();
        criteria.setUserId(uid);
        return courseOrderMapper.selectList(criteria);
    }

    private void check(CourseOrder order) {
        if (null == order) {
            throw new SteamException(ErrorEnum.ORDER_NOT_EXIST_ERR.getCode(), ErrorEnum.ORDER_NOT_EXIST_ERR.getMessage());
        }

        if (!OrderStatusEnum.DRAFT.getCode().equals(order.getStatus())) {
            throw new SteamException(ErrorEnum.ORDER_PAY_ERR.getCode(), ErrorEnum.ORDER_PAY_ERR.getMessage());
        }
    }
}
