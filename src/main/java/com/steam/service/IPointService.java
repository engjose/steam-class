package com.steam.service;

import com.steam.model.po.Point;

import java.util.List;

/**
 * @author : JOSE 2019/3/18 11:53 PM
 */
public interface IPointService {

    /**
     * get point list
     *
     * @param uid {param-0}
     * @return 返回积分列表
     */
    List<Point> getPointList(String uid);

    /**
     * add point
     *
     * @param source 来源
     * @param value 分值
     */
    void addPoint(String uid, String source, int value);
}
