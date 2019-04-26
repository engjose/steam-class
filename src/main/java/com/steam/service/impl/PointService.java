package com.steam.service.impl;

import com.steam.dao.PointMapper;
import com.steam.dao.UserMapper;
import com.steam.model.po.Point;
import com.steam.model.po.PointExt;
import com.steam.model.po.User;
import com.steam.service.IPointService;
import com.steam.service.IUserService;
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

    @Autowired
    private IUserService userService;

    @Autowired
    private UserMapper userMapper;

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

        User user = userService.selectByUid(uid);
        User userRecord = new User();
        userRecord.setId(user.getId());
        userRecord.setPoint(user.getPoint() + value);
        userMapper.updateByPrimaryKeySelective(userRecord);
    }
}
