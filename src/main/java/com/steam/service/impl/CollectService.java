package com.steam.service.impl;

import com.google.common.collect.Lists;
import com.steam.common.CollectEnum;
import com.steam.common.CourseTypeEnum;
import com.steam.common.PriceTypeEnum;
import com.steam.common.SteamException;
import com.steam.dao.CourseCollectMapper;
import com.steam.model.po.Course;
import com.steam.model.po.CourseCollect;
import com.steam.model.po.CourseCollectExt;
import com.steam.model.vo.QueryCourseItem;
import com.steam.service.ICollectService;
import com.steam.service.ICourseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author : JOSE 2019/4/22 9:01 PM
 */
@Service
public class CollectService implements ICollectService {

    @Autowired
    private ICourseService courseService;

    @Autowired
    private CourseCollectMapper courseCollectMapper;

    @Override
    public void collect(String uid, String courseId) {
        CourseCollectExt criteria = new CourseCollectExt();
        criteria.setUserId(uid);
        criteria.setCourseId(courseId);
        CourseCollect courseCollect = selectOne(criteria);
        if (courseCollect != null) {
            CourseCollect updateRecord = new CourseCollect();
            updateRecord.setId(courseCollect.getId());
            updateRecord.setIsCollect(CollectEnum.COLLECT.getCode());
            courseCollectMapper.updateByPrimaryKeySelective(updateRecord);
            return;
        }

        // 第一次收藏
        CourseCollect record = new CourseCollect();
        Course course = courseService.selectByCourseId(courseId);
        record.setUserId(uid);
        record.setCourseId(course.getCourseId());
        record.setIsCollect(CollectEnum.COLLECT.getCode());
        courseCollectMapper.insertSelective(record);
    }

    @Override
    public void unCollect(String uid, String courseId) {
        CourseCollectExt criteria = new CourseCollectExt();
        criteria.setUserId(uid);
        criteria.setCourseId(courseId);
        CourseCollect courseCollect = selectOne(criteria);
        if (courseCollect == null) {
            throw new SteamException(40008, "请先收藏课程");
        }

        CourseCollect updateRecord = new CourseCollect();
        updateRecord.setId(courseCollect.getId());
        updateRecord.setIsCollect(CollectEnum.UN_COLLECT.getCode());
        courseCollectMapper.updateByPrimaryKeySelective(updateRecord);
    }

    @Override
    public List<QueryCourseItem> selectCollectList(String uid) {
        CourseCollectExt criteria = new CourseCollectExt();
        criteria.setUserId(uid);
        criteria.setIsCollect(CollectEnum.COLLECT.getCode());
        List<CourseCollect> collectList = courseCollectMapper.selectList(criteria);

        List<QueryCourseItem> resultList = Lists.newArrayList();
        if (CollectionUtils.isEmpty(collectList)) {
            return resultList;
        }

        collectList.forEach(item -> {
            QueryCourseItem courseItem = new QueryCourseItem();
            Course course = courseService.selectByCourseId(item.getCourseId());
            BeanUtils.copyProperties(course, courseItem);
            courseItem.setIsBuy(courseService.isBuy(uid, item.getCourseId()));
            courseItem.setIsCollect(CollectEnum.COLLECT.getCode());
            courseItem.setPriceTypeDesc(PriceTypeEnum.mappingDesc(course.getPriceType()));
            courseItem.setCourseTypeDesc(CourseTypeEnum.mappingDesc(course.getCourseType()));
            resultList.add(courseItem);
        });

        return resultList;
    }

    @Override
    public CourseCollect selectOne(CourseCollectExt record) {
        return courseCollectMapper.selectOne(record);
    }

}
