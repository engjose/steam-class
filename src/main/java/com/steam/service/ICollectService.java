package com.steam.service;

import com.steam.model.po.CourseCollect;
import com.steam.model.po.CourseCollectExt;
import com.steam.model.vo.QueryCourseItem;

import java.util.List;

/**
 * @author : JOSE 2019/4/22 9:01 PM
 */
public interface ICollectService {

    /**
     * collect course
     *
     * @param uid uid
     * @param courseId course id
     */
    void collect(String uid, String courseId);

    /**
     * dis collect course
     *
     * @param uid uid
     * @param courseId course id
     */
    void unCollect(String uid, String courseId);

    /**
     * get collect course list
     *
     * @param uid uid
     * @return course list
     */
    List<QueryCourseItem> selectCollectList(String uid);

    /**
     * select one
     *
     * @return collect course
     */
    CourseCollect selectOne(CourseCollectExt record);
}
