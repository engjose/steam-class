package com.steam.model.po;

import java.math.BigDecimal;
import java.util.Date;

public class CourseOrder {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column course_order.id
     *
     * @mbggenerated Sat Apr 20 19:46:39 CST 2019
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column course_order.order_id
     *
     * @mbggenerated Sat Apr 20 19:46:39 CST 2019
     */
    private String orderId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column course_order.user_id
     *
     * @mbggenerated Sat Apr 20 19:46:39 CST 2019
     */
    private String userId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column course_order.course_id
     *
     * @mbggenerated Sat Apr 20 19:46:39 CST 2019
     */
    private String courseId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column course_order.course_name
     *
     * @mbggenerated Sat Apr 20 19:46:39 CST 2019
     */
    private String courseName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column course_order.price_type
     *
     * @mbggenerated Sat Apr 20 19:46:39 CST 2019
     */
    private String priceType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column course_order.status
     *
     * @mbggenerated Sat Apr 20 19:46:39 CST 2019
     */
    private String status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column course_order.price
     *
     * @mbggenerated Sat Apr 20 19:46:39 CST 2019
     */
    private BigDecimal price;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column course_order.create_time
     *
     * @mbggenerated Sat Apr 20 19:46:39 CST 2019
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column course_order.update_time
     *
     * @mbggenerated Sat Apr 20 19:46:39 CST 2019
     */
    private Date updateTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column course_order.id
     *
     * @return the value of course_order.id
     *
     * @mbggenerated Sat Apr 20 19:46:39 CST 2019
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column course_order.id
     *
     * @param id the value for course_order.id
     *
     * @mbggenerated Sat Apr 20 19:46:39 CST 2019
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column course_order.order_id
     *
     * @return the value of course_order.order_id
     *
     * @mbggenerated Sat Apr 20 19:46:39 CST 2019
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column course_order.order_id
     *
     * @param orderId the value for course_order.order_id
     *
     * @mbggenerated Sat Apr 20 19:46:39 CST 2019
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column course_order.user_id
     *
     * @return the value of course_order.user_id
     *
     * @mbggenerated Sat Apr 20 19:46:39 CST 2019
     */
    public String getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column course_order.user_id
     *
     * @param userId the value for course_order.user_id
     *
     * @mbggenerated Sat Apr 20 19:46:39 CST 2019
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column course_order.course_id
     *
     * @return the value of course_order.course_id
     *
     * @mbggenerated Sat Apr 20 19:46:39 CST 2019
     */
    public String getCourseId() {
        return courseId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column course_order.course_id
     *
     * @param courseId the value for course_order.course_id
     *
     * @mbggenerated Sat Apr 20 19:46:39 CST 2019
     */
    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column course_order.course_name
     *
     * @return the value of course_order.course_name
     *
     * @mbggenerated Sat Apr 20 19:46:39 CST 2019
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column course_order.course_name
     *
     * @param courseName the value for course_order.course_name
     *
     * @mbggenerated Sat Apr 20 19:46:39 CST 2019
     */
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column course_order.price_type
     *
     * @return the value of course_order.price_type
     *
     * @mbggenerated Sat Apr 20 19:46:39 CST 2019
     */
    public String getPriceType() {
        return priceType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column course_order.price_type
     *
     * @param priceType the value for course_order.price_type
     *
     * @mbggenerated Sat Apr 20 19:46:39 CST 2019
     */
    public void setPriceType(String priceType) {
        this.priceType = priceType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column course_order.status
     *
     * @return the value of course_order.status
     *
     * @mbggenerated Sat Apr 20 19:46:39 CST 2019
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column course_order.status
     *
     * @param status the value for course_order.status
     *
     * @mbggenerated Sat Apr 20 19:46:39 CST 2019
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column course_order.price
     *
     * @return the value of course_order.price
     *
     * @mbggenerated Sat Apr 20 19:46:39 CST 2019
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column course_order.price
     *
     * @param price the value for course_order.price
     *
     * @mbggenerated Sat Apr 20 19:46:39 CST 2019
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column course_order.create_time
     *
     * @return the value of course_order.create_time
     *
     * @mbggenerated Sat Apr 20 19:46:39 CST 2019
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column course_order.create_time
     *
     * @param createTime the value for course_order.create_time
     *
     * @mbggenerated Sat Apr 20 19:46:39 CST 2019
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column course_order.update_time
     *
     * @return the value of course_order.update_time
     *
     * @mbggenerated Sat Apr 20 19:46:39 CST 2019
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column course_order.update_time
     *
     * @param updateTime the value for course_order.update_time
     *
     * @mbggenerated Sat Apr 20 19:46:39 CST 2019
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}