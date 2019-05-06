package com.steam.service.impl;

import com.steam.common.*;
import com.steam.dao.UserLoginMapper;
import com.steam.dao.UserMapper;
import com.steam.model.po.*;
import com.steam.model.vo.*;
import com.steam.service.IOrderService;
import com.steam.service.IPointService;
import com.steam.service.IUserService;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : JOSE 2019/3/11 8:34 PM
 */
@Service
public class UserService implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserLoginMapper userLoginMapper;

    @Autowired
    private IPointService pointService;

    @Autowired
    private IOrderService orderService;

    @Value("${registerPoint}")
    private int registerPoint;

    @Value("${headPic}")
    private String headPic;

    @Transactional
    @Override
    public UserLoginResponse login(UserLoginRequest request) {
        String encodePassword = MD5Util.MD5EncodeUtf8(request.getPassword(), request.getNickName()); //加密的密码

        User criteria = new User();
        criteria.setNickName(request.getNickName());
        criteria.setPassword(encodePassword);
        List<User> userList = userMapper.selectList(criteria);

        // 检查用户状态等信息
        loginCheck(userList, request.getRole());

        // 产生登录token
        UserLoginResponse response = new UserLoginResponse();
        String token = TokenUtil.getToken("LOGIN");
        response.setToken(token);

        //设置登录信息: 1小时免登录
        UserLogin record = new UserLogin();
        record.setToken(token);
        record.setLoginTime(new Date());
        record.setExpiredTime(DateUtils.addHours(new Date(), 1));
        record.setUid(userList.get(0).getUid());

        // 插入登录信息
        userLoginMapper.insertSelective(record);
        return response;
    }

    @Transactional
    @Override
    public void register(UserLoginRequest request) {
        // 查询用户名是否已经存在
        User criteria = new User();
        criteria.setNickName(request.getNickName());
        List<User> userList = userMapper.selectList(criteria);
        if (!CollectionUtils.isEmpty(userList)) {
            throw new SteamException(ErrorEnum.USER_EXISTS.getCode(), ErrorEnum.USER_EXISTS.getMessage());
        }

        // 添加注册用户
        String uid = TokenUtil.getToken("UID");
        User record = new User();
        record.setNickName(request.getNickName());
        record.setPassword(MD5Util.MD5EncodeUtf8(request.getPassword(), request.getNickName()));
        record.setRole(RoleEnum.USER.getCode());
        record.setStatus(UserStatusEnum.ACTIVE.getCode());
        record.setHeadPic(headPic);
        record.setUid(uid);
        userMapper.insertSelective(record);

        // 添加积分
        pointService.addPoint(uid, PointSourceEnum.REGISTRY.getCode(),registerPoint);
    }

    @Override
    public String checkToken(String token) {
        if (StringUtils.isBlank(token)) {
            throw new SteamException(ErrorEnum.TOKEN_UN_EFFECTIVE.getCode(), ErrorEnum.TOKEN_UN_EFFECTIVE.getMessage());
        }

        UserLoginExt criteria = new UserLoginExt();
        criteria.setToken(token);
        criteria.setNowTime(new Date());
        List<UserLogin> userLoginList = userLoginMapper.selectList(criteria);
        if (CollectionUtils.isEmpty(userLoginList)) {
            throw new SteamException(ErrorEnum.TOKEN_UN_EFFECTIVE.getCode(), ErrorEnum.TOKEN_UN_EFFECTIVE.getMessage());
        }

        return userLoginList.get(0).getUid();
    }

    @Override
    public String isLogin(String token) {
        if (StringUtils.isBlank(token)) {
            return null;
        }

        UserLoginExt criteria = new UserLoginExt();
        criteria.setToken(token);
        criteria.setNowTime(new Date());
        List<UserLogin> userLoginList = userLoginMapper.selectList(criteria);
        if (CollectionUtils.isEmpty(userLoginList)) {
            return null;
        }

        return userLoginList.get(0).getUid();
    }

    @Override
    public UserCenterResponse getUserCenter(String token) {

        // 校验token
        String uid = checkToken(token);

        // 查询用户信息
        User user = selectByUid(uid);

        // 查询积分明细
        List<Point> pointList = pointService.getPointList(uid);

        // 查询订单信息
        List<CourseOrder> orderList = orderService.selectList(uid);

        return packageUserCenter(user, pointList, orderList);
    }

    @Override
    public User selectByUid(String uid) {
        UserExt criteria = new UserExt();
        criteria.setUid(uid);
        List<User> userList = userMapper.selectList(criteria);
        return CollectionUtils.isEmpty(userList) ? null : userList.get(0);
    }

    @Override
    public void updatePassword(String token, String password) {
        // check token
        String uid = checkToken(token);

        // 设置登录失效
        setTokenUnEffective(token);

        // 从新设置密码
        User user = selectByUid(uid);
        User record = new User();
        record.setId(user.getId());
        record.setPassword(MD5Util.MD5EncodeUtf8(password, user.getNickName()));
        userMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public UserLogin selectLoginByToken(String token) {
        UserLoginExt criteria = new UserLoginExt();
        criteria.setToken(token);
        criteria.setNowTime(new Date());
        List<UserLogin> loginList = userLoginMapper.selectList(criteria);
        return loginList != null && loginList.size() > 0 ? loginList.get(0) : null;
    }

    private void loginCheck(List<User> userList, String role) {
        // 用户名或者密码错误
        if (CollectionUtils.isEmpty(userList)) {
            throw new SteamException(ErrorEnum.USER_NAME_OR_PASSWORD_ERR.getCode(), ErrorEnum.USER_NAME_OR_PASSWORD_ERR.getMessage());
        }

        // 用户状态已经冻结
        User user = userList.get(0);
        if (userList.size() != 1 || UserStatusEnum.UN_ACTIVE.getCode().equals(user.getStatus())) {
            throw new SteamException(ErrorEnum.USER_STATUS_ERR.getCode(),ErrorEnum.USER_STATUS_ERR.getMessage());
        }

        // 用户角色不正确
        if (!user.getRole().equals(role)) {
            throw new SteamException(ErrorEnum.USER_ROLE_ERR.getCode(), ErrorEnum.USER_ROLE_ERR.getMessage());
        }

        // 重复登录
        UserLoginExt criteria = new UserLoginExt();
        criteria.setUid(user.getUid());
        criteria.setNowTime(new Date());
        List<UserLogin> loginRecordList = userLoginMapper.selectList(criteria);
        if (!CollectionUtils.isEmpty(loginRecordList)) {
            setTokenUnEffective(loginRecordList.get(0).getToken());
        }
    }

    private UserCenterResponse packageUserCenter(User user, List<Point> pointList, List<CourseOrder> orderList) {
        UserCenterResponse response = new UserCenterResponse();

        // 用户信息
        UserResponse userInfo = new UserResponse();
        userInfo.setNickName(user.getNickName());
        userInfo.setHeadPic(user.getHeadPic());
        userInfo.setRegisterDate(DateFormatUtils.format(user.getCreateTime(), "yyyy-MM-dd"));

        // 积分信息
        PointResponse point = buildPointInfo(pointList, user);

        // 订单信息
        OrderResponse orderInfo = buildOrderInfo(orderList);

        response.setUserInfo(userInfo);
        response.setPoint(point);
        response.setOrderInfo(orderInfo);
        return response;
    }

    private void setTokenUnEffective(String token) {
        UserLogin userLogin = selectLoginByToken(token);
        if (userLogin == null) {
            return;
        }

        UserLogin record = new UserLogin();
        BeanUtils.copyProperties(userLogin, record);
        record.setExpiredTime(new Date());
        userLoginMapper.updateByPrimaryKey(record);
    }

    private OrderResponse buildOrderInfo(List<CourseOrder> orderList) {
        OrderResponse orderInfo = new OrderResponse();
        if (!CollectionUtils.isEmpty(orderList)) {
            for (CourseOrder item : orderList) {
                OrderItem orderItem = new OrderItem();
                BeanUtils.copyProperties(item, orderItem);
                orderItem.setCreateTime(DateFormatUtils.format(item.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
                orderItem.setPriceTypeDesc(PriceTypeEnum.mappingDesc(item.getPriceType()));
                orderItem.setStatusDesc(OrderStatusEnum.mappingDesc(item.getStatus()));
                orderItem.setCourseTypeDesc(CourseTypeEnum.mappingDesc(item.getCourseType()));

                if (OrderStatusEnum.DRAFT.getCode().equals(item.getStatus())) {
                    orderInfo.getDraftOrderList().add(orderItem);
                }
                else if (OrderStatusEnum.PAYED.getCode().equals(item.getStatus())) {
                    orderInfo.getPayOrderList().add(orderItem);
                }
                else if (OrderStatusEnum.CANCEL.getCode().equals(item.getStatus())){
                    orderInfo.getCancelOrderList().add(orderItem);
                }
            }
        }

        return orderInfo;
    }

    private PointResponse buildPointInfo(List<Point> pointList, User user) {
        PointResponse point = new PointResponse();
        point.setTotalPoint(user.getPoint());
        List<PointItem> pointItemList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(pointList)) {
            pointList.forEach(item -> {
                PointItem pointItem = new PointItem();
                pointItem.setValue(item.getPointValue());
                pointItem.setSource(PointSourceEnum.mappingDesc(item.getSource()));
                pointItemList.add(pointItem);
            });
        }
        point.setPointList(pointItemList);
        return point;
    }
}
