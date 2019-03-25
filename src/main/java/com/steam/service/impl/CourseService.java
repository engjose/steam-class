package com.steam.service.impl;

import com.steam.common.CourseTypeEnum;
import com.steam.dao.CourseMapper;
import com.steam.model.po.Course;
import com.steam.model.po.CourseExt;
import com.steam.model.vo.QueryCourseItem;
import com.steam.model.vo.QueryCourseRequest;
import com.steam.model.vo.QueryCourseResponse;
import com.steam.service.ICourseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : JOSE 2019/3/25 9:54 PM
 */
@Service
public class CourseService implements ICourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public QueryCourseResponse selectCourseList(QueryCourseRequest request) {
        CourseExt criteria = new CourseExt();
        BeanUtils.copyProperties(request, criteria);
        List<Course> courseList = courseMapper.selectList(criteria);
        return packageCourseResponse(courseList);
    }

    private QueryCourseResponse packageCourseResponse(List<Course> courseList) {
        QueryCourseResponse response = new QueryCourseResponse();
        if (courseList == null || courseList.size() == 0) {
            return response;
        }

        List<QueryCourseItem> resultList = new ArrayList<>();
        courseList.forEach(item -> {
            QueryCourseItem resultItem = new QueryCourseItem();
            BeanUtils.copyProperties(item, resultItem);
            resultItem.setCourseTypeDesc(CourseTypeEnum.mappingDesc(item.getCourseType()));
            resultList.add(resultItem);
        });

        response.setCourseList(resultList);
        return response;
    }
}
