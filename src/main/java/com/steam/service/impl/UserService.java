package com.steam.service.impl;

import com.steam.common.*;
import com.steam.dao.UserLoginMapper;
import com.steam.dao.UserMapper;
import com.steam.model.po.User;
import com.steam.model.po.UserLogin;
import com.steam.model.po.UserLoginExt;
import com.steam.model.vo.UserLoginRequest;
import com.steam.model.vo.UserLoginResponse;
import com.steam.service.IUserService;
import org.apache.commons.lang.time.DateUtils;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

/**
 * @author : JOSE 2019/3/11 8:34 PM
 */
@Service
public class UserService implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserLoginMapper userLoginMapper;

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
        loginCheck(userList);

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
        User record = new User();
        record.setNickName(request.getNickName());
        record.setPassword(MD5Util.MD5EncodeUtf8(request.getPassword(), request.getNickName()));
        record.setPoint(registerPoint);
        record.setRole(RoleEnum.USER.getCode());
        record.setStatus(UserStatusEnum.ACTIVE.getCode());
        record.setHeadPic(headPic);
        record.setUid(TokenUtil.getToken("UID"));
        userMapper.insertSelective(record);
    }

    private void loginCheck(List<User> userList ) {
        // 用户名或者密码错误
        if (CollectionUtils.isEmpty(userList)) {
            throw new SteamException(ErrorEnum.USER_NAME_OR_PASSWORD_ERR.getCode(), ErrorEnum.USER_NAME_OR_PASSWORD_ERR.getMessage());
        }

        // 用户状态已经冻结
        if (userList.size() != 1 || UserStatusEnum.UN_ACTIVE.getCode().equals(userList.get(0).getStatus())) {
            throw new SteamException(ErrorEnum.USER_STATUS_ERR.getCode(),ErrorEnum.USER_STATUS_ERR.getMessage());
        }

        // 重复登录
        UserLoginExt criteria = new UserLoginExt();
        criteria.setUid(userList.get(0).getUid());
        criteria.setNowTime(new Date());
        List<UserLogin> loginRecordList = userLoginMapper.selectList(criteria);
        if (!CollectionUtils.isEmpty(loginRecordList)) {
            throw new SteamException(ErrorEnum.LOGIN_REPEAT.getCode(), ErrorEnum.LOGIN_REPEAT.getMessage());
        }
    }
}
