package com.steam.service;

import com.steam.model.vo.QueryCourseRequest;
import com.steam.model.vo.QueryCourseResponse;

/**
 * @author : JOSE 2019/3/25 9:53 PM
 */
public interface ICourseService {

    /**
     * select course list
     *
     * @param request param{0}: select param
     * @return select response
     */
    QueryCourseResponse selectCourseList(QueryCourseRequest request);
}
