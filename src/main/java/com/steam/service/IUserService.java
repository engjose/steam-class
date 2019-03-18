package com.steam.service;

import com.steam.model.po.User;
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
}
