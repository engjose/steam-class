package com.steam.service;

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
}
