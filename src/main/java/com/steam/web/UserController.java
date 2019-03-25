package com.steam.web;

import com.steam.common.ErrorEnum;
import com.steam.common.SteamException;
import com.steam.model.vo.*;
import com.steam.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author : JOSE 2019/3/11 8:22 PM
 */
@Api(tags = "【用户】服务")
@RefreshScope
@RestController
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class UserController {

    @Autowired
    private IUserService userService;

    @ApiOperation("用户登录")
    @PostMapping(value = "/login")
    public UserLoginResponse login(@Valid @RequestBody UserLoginRequest userInfo) {
        if (StringUtils.isBlank(userInfo.getRole()) || userInfo.getRole().length() != 1) {
            throw new SteamException(ErrorEnum.USER_ROLE_ERR.getCode(), ErrorEnum.USER_ROLE_ERR.getMessage());
        }

        return userService.login(userInfo);
    }

    @ApiOperation("用户登录")
    @PostMapping(value = "/register")
    public BaseResponse register(@Valid @RequestBody UserLoginRequest userInfo) {
        userService.register(userInfo);
        return new BaseResponse();
    }

    @ApiOperation("用户中心")
    @PostMapping(value = "/center")
    public UserCenterResponse getUserInfo(@RequestBody BaseRequest request) {
        return  userService.getUserCenter(request.getToken());
    }

    @ApiOperation("修改密码")
    @PostMapping(value = "/update/password")
    public BaseResponse updatePassword(@Valid @RequestBody UpdatePasswordRequest request) {
        userService.updatePassword(request.getToken(), request.getPassword());
        return new BaseResponse();
    }
}
