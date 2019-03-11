package com.steam.service.impl;

import com.steam.common.ErrorEnum;
import com.steam.common.SteamException;
import com.steam.common.TokenUtil;
import com.steam.dao.UserLoginMapper;
import com.steam.dao.UserMapper;
import com.steam.model.po.User;
import com.steam.model.po.UserLogin;
import com.steam.model.vo.UserLoginRequest;
import com.steam.model.vo.UserLoginResponse;
import com.steam.service.IUserService;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Transactional
    @Override
    public UserLoginResponse login(UserLoginRequest request) {
        User criteria = new User();
        criteria.setNickName(request.getNickName());
        criteria.setPassword(request.getPassword());
        List<User> userList = userMapper.selectList(criteria);

        // 用户名或者密码错误
        if (CollectionUtils.isEmpty(userList) || userList.size() != 1) {
            throw new SteamException(ErrorEnum.USER_NAME_OR_PASSWORD_ERR.getCode(), ErrorEnum.USER_NAME_OR_PASSWORD_ERR.getMessage());
        }

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

    }
}
