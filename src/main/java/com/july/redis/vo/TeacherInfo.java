package com.july.redis.vo;

import java.io.Serializable;

/**
 * @author july
 */
public class TeacherInfo implements Serializable {

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * 业务主键
     */
    private String id;
    /**
     * 用户编码
     */
    private String userCode;
    /**
     * 姓名
     */
    private String name;
    /**
     * 性别
     */
    private String sex;

}
