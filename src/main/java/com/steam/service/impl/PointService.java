package com.steam.service.impl;

import com.steam.dao.PointMapper;
import com.steam.model.po.Point;
import com.steam.model.po.PointExt;
import com.steam.service.IPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author : JOSE 2019/3/18 11:55 PM
 */
@Service
public class PointService implements IPointService {

    @Autowired
    private PointMapper pointMapper;

    @Override
    public List<Point> getPointList(String uid) {
        PointExt criteria = new PointExt();
        criteria.setUid(uid);
        return pointMapper.selectList(criteria);
    }

    @Transactional
    @Override
    public void addPoint(String uid, String source, int value) {
        Point record = new Point();
        record.setUid(uid);
        record.setSource(source);
        record.setPointValue(value);
        pointMapper.insertSelective(record);
    }
}
