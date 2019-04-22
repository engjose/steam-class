package com.steam.model.po;

import java.util.Date;

public class CourseCollect {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column course_collect.id
     *
     * @mbggenerated Mon Apr 22 20:47:56 CST 2019
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column course_collect.course_id
     *
     * @mbggenerated Mon Apr 22 20:47:56 CST 2019
     */
    private String courseId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column course_collect.user_id
     *
     * @mbggenerated Mon Apr 22 20:47:56 CST 2019
     */
    private String userId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column course_collect.is_collect
     *
     * @mbggenerated Mon Apr 22 20:47:56 CST 2019
     */
    private String isCollect;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column course_collect.create_time
     *
     * @mbggenerated Mon Apr 22 20:47:56 CST 2019
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column course_collect.update_time
     *
     * @mbggenerated Mon Apr 22 20:47:56 CST 2019
     */
    private Date updateTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column course_collect.id
     *
     * @return the value of course_collect.id
     *
     * @mbggenerated Mon Apr 22 20:47:56 CST 2019
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column course_collect.id
     *
     * @param id the value for course_collect.id
     *
     * @mbggenerated Mon Apr 22 20:47:56 CST 2019
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column course_collect.course_id
     *
     * @return the value of course_collect.course_id
     *
     * @mbggenerated Mon Apr 22 20:47:56 CST 2019
     */
    public String getCourseId() {
        return courseId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column course_collect.course_id
     *
     * @param courseId the value for course_collect.course_id
     *
     * @mbggenerated Mon Apr 22 20:47:56 CST 2019
     */
    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column course_collect.user_id
     *
     * @return the value of course_collect.user_id
     *
     * @mbggenerated Mon Apr 22 20:47:56 CST 2019
     */
    public String getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column course_collect.user_id
     *
     * @param userId the value for course_collect.user_id
     *
     * @mbggenerated Mon Apr 22 20:47:56 CST 2019
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column course_collect.is_collect
     *
     * @return the value of course_collect.is_collect
     *
     * @mbggenerated Mon Apr 22 20:47:56 CST 2019
     */
    public String getIsCollect() {
        return isCollect;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column course_collect.is_collect
     *
     * @param isCollect the value for course_collect.is_collect
     *
     * @mbggenerated Mon Apr 22 20:47:56 CST 2019
     */
    public void setIsCollect(String isCollect) {
        this.isCollect = isCollect;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column course_collect.create_time
     *
     * @return the value of course_collect.create_time
     *
     * @mbggenerated Mon Apr 22 20:47:56 CST 2019
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column course_collect.create_time
     *
     * @param createTime the value for course_collect.create_time
     *
     * @mbggenerated Mon Apr 22 20:47:56 CST 2019
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column course_collect.update_time
     *
     * @return the value of course_collect.update_time
     *
     * @mbggenerated Mon Apr 22 20:47:56 CST 2019
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column course_collect.update_time
     *
     * @param updateTime the value for course_collect.update_time
     *
     * @mbggenerated Mon Apr 22 20:47:56 CST 2019
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}