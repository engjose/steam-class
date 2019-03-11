package com.steam.web;

import com.steam.model.vo.UserLoginRequest;
import com.steam.model.vo.UserLoginResponse;
import com.steam.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
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
        return userService.login(userInfo);
    }

    @ApiOperation("用户登录")
    @PostMapping(value = "/register")
    public void register(@Valid @RequestBody UserLoginRequest userInfo) {

    }
}
