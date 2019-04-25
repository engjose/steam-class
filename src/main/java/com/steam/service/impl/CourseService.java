package com.steam.service.impl;

import com.steam.common.CourseTypeEnum;
import com.steam.common.ErrorEnum;
import com.steam.common.OrderStatusEnum;
import com.steam.common.SteamException;
import com.steam.dao.CourseMapper;
import com.steam.model.po.Course;
import com.steam.model.po.CourseExt;
import com.steam.model.po.CourseOrder;
import com.steam.model.vo.QueryCourseItem;
import com.steam.model.vo.QueryCourseRequest;
import com.steam.model.vo.QueryCourseResponse;
import com.steam.service.ICollectService;
import com.steam.service.ICourseService;
import com.steam.service.IOrderService;
import com.steam.service.IUserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : JOSE 2019/3/25 9:54 PM
 */
@Service
public class CourseService implements ICourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private IOrderService iOrderService;

    @Autowired
    private IUserService iUserService;

    @Autowired
    private ICollectService iCollectService;

    @Override
    public QueryCourseResponse selectCourseList(QueryCourseRequest request) {
        CourseExt criteria = new CourseExt();
        BeanUtils.copyProperties(request, criteria);
        List<Course> courseList = courseMapper.selectList(criteria);

        String uid = iUserService.isLogin(request.getToken());
        return packageCourseResponse(courseList, uid);
    }

    @Override
    public Course selectByCourseId(String courseId) {
        if (StringUtils.isBlank(courseId)) {
            throw new SteamException(ErrorEnum.SYS_ERR.getCode(), ErrorEnum.SYS_ERR.getMessage());
        }

        CourseExt criteria = new CourseExt();
        criteria.setCourseId(courseId);
        List<Course> courseList = courseMapper.selectList(criteria);
        return CollectionUtils.isEmpty(courseList) ? null : courseList.get(0);
    }

    @Override
    public Boolean isBuy(String uid, String courseId) {
        if (StringUtils.isNotBlank(uid)) {
            CourseOrder order = iOrderService.selectByUidAndCourseId(uid, courseId);
            return order != null && OrderStatusEnum.PAYED.getCode().equals(order.getStatus());
        }

        return false;
    }

    private QueryCourseResponse packageCourseResponse(List<Course> courseList, String uid) {
        QueryCourseResponse response = new QueryCourseResponse();
        if (courseList == null || courseList.size() == 0) {
            return response;
        }

        List<QueryCourseItem> resultList = new ArrayList<>();
        courseList.forEach(item -> {
            QueryCourseItem resultItem = new QueryCourseItem();
            BeanUtils.copyProperties(item, resultItem);
            resultItem.setCourseTypeDesc(CourseTypeEnum.mappingDesc(item.getCourseType()));

            // 是否购买课程
            resultItem.setIsBuy(isBuy(uid, item.getCourseId()));
            resultItem.setIsCollect(iCollectService.isCollect(uid, item.getCourseId()));

            resultList.add(resultItem);
        });

        response.setCourseList(resultList);
        return response;
    }
}
