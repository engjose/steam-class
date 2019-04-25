package com.steam.web;

import com.steam.common.CourseTypeEnum;
import com.steam.common.ErrorEnum;
import com.steam.common.PriceTypeEnum;
import com.steam.common.SteamException;
import com.steam.model.po.Course;
import com.steam.model.vo.QueryCourseItem;
import com.steam.model.vo.QueryCourseRequest;
import com.steam.model.vo.QueryCourseResponse;
import com.steam.service.ICollectService;
import com.steam.service.ICourseService;
import com.steam.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : JOSE 2019/3/25 9:45 PM
 */
@Api(tags = "【课程】服务")
@RefreshScope
@RestController
@RequestMapping(value = "/course", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class CourseController {

    @Autowired
    private ICourseService courseService;

    @Autowired
    private IUserService userService;

    @Autowired
    private ICollectService iCollectService;

    @ApiOperation("查询课程列表")
    @PostMapping(value = "/list")
    QueryCourseResponse selectCourseList(@RequestBody QueryCourseRequest request) {
        String priceSort = request.getPriceSort();
        if (!StringUtils.isBlank(priceSort) && !"1".equals(priceSort) && !"0".equals(priceSort)) {
            throw new SteamException(ErrorEnum.SORT_RULE_ERR.getCode(), ErrorEnum.SORT_RULE_ERR.getMessage());
        }

        return courseService.selectCourseList(request);
    }

    @ApiOperation("查询课程列表")
    @PostMapping(value = "/detail")
    QueryCourseItem selectCourseByCourseId(@RequestBody QueryCourseRequest request) {
        if (StringUtils.isBlank(request.getCourseId())) {
            throw new SteamException(ErrorEnum.COURSE_ID_ERR.getCode(), ErrorEnum.COURSE_ID_ERR.getMessage());
        }

        String uid = userService.checkToken(request.getToken());
        Course course = courseService.selectByCourseId(request.getCourseId());
        if (course == null) {
            return null;
        }

        QueryCourseItem result = new QueryCourseItem();
        BeanUtils.copyProperties(course, result);
        result.setIsBuy(courseService.isBuy(uid, course.getCourseId()));
        result.setIsCollect(iCollectService.isCollect(uid, course.getCourseId()));
        result.setCourseTypeDesc(CourseTypeEnum.mappingDesc(course.getCourseType()));
        result.setPriceTypeDesc(PriceTypeEnum.mappingDesc(course.getPriceType()));
        return result;
    }
}
