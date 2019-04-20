package com.steam.service;

import com.steam.model.po.User;
import com.steam.model.po.UserLogin;
import com.steam.model.vo.UserCenterResponse;
import com.steam.model.vo.UserLoginRequest;
import com.steam.model.vo.UserLoginResponse;

/**
 * @author : JOSE 2019/3/11 8:34 PM
 */
public interface IUserService {

    /**
     * user login
     *
     * @param request {param-0}: user info
     */
    UserLoginResponse login(UserLoginRequest request);

    /**
     *
     * @param request
     * @return
     */
    void register(UserLoginRequest request);

    /**
     * check token
     *
     * @param token {param-0}: token
     */
    String checkToken(String token);

    /**
     * 校验是否登录
     *
     * @param token token
     * @return 登录了返回uid
     */
    String isLogin(String token);

    /**
     * get user center
     *
     * @param token token
     * @return return userResponse
     */
    UserCenterResponse getUserCenter(String token);

    /**
     * select by uid
     *
     * @param uid uid
     * @return return user po
     */
    User selectByUid(String uid);

    /**
     * update user password
     *
     * @param token token
     */
    void updatePassword(String token, String password);

    /**
     * select login record by token
     *
     * @param token param{1}: token
     * @return user login record
     */
    UserLogin selectLoginByToken(String token);
}
