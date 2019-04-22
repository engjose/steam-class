package com.steam.web;

import com.steam.model.vo.*;
import com.steam.service.ICollectService;
import com.steam.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author : JOSE 2019/4/22 10:23 PM
 */
@Api(tags = "【收藏】服务")
@RefreshScope
@RestController
@RequestMapping(value = "/collect", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class CollectController {

    @Autowired
    private ICollectService collectService;

    @Autowired
    private IUserService userService;

    @ApiOperation("收藏课程")
    @PostMapping(value = "/add")
    BaseResponse collectCourse(@RequestBody CollectRequest request) {
        String uid = userService.checkToken(request.getToken());
        collectService.collect(uid, request.getCourseId());
        return new BaseResponse();
    }

    @ApiOperation("取消收藏")
    @PostMapping(value = "/cancel")
    BaseResponse unCollectCourse(@RequestBody CollectRequest request) {
        String uid = userService.checkToken(request.getToken());
        collectService.unCollect(uid, request.getCourseId());
        return new BaseResponse();
    }

    @ApiOperation("查询收藏列表")
    @PostMapping(value = "/list")
    QueryCourseResponse selectCollectList(@RequestBody BaseRequest request) {
        String uid = userService.checkToken(request.getToken());
        List<QueryCourseItem> courseList = collectService.selectCollectList(uid);

        QueryCourseResponse response = new QueryCourseResponse();
        response.setCourseList(courseList);
        return response;
    }
}
