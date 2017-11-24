package com.july.redis;

import com.july.redis.vo.StudentInfo;
import com.july.redis.vo.TeacherInfo;

import java.util.List;
import java.util.Map;

/**
 * @author july
 */
public interface RedisCacheServices {
    /**
     *学生code
     */
    public static final String REDIS_STUDENT_CODE_KEY="REDIS_STUDENT_CODE_KEY";
    /**
     *老师key
     */
    public static final String REDIS_TEACHER_ID_KEY="REDIS_TEACHER_ID_KEY";
    /**
     * 根据userCodeOrId保存学生信息
     */
    public void saveStudentInfo(String userCodeOrId,StudentInfo studentInfo);
    /***
     * 根据userCode或者ID查询学生信息
     * @param userCodeOrId
     * @return
     */
    public StudentInfo getStudentInfo(String userCodeOrId);

    public void saveTeacherInfo(String teacherId,TeacherInfo teacherInfo);
    /**
     * 根据教师id查询老师信息
     */
    public TeacherInfo getTeacherInfoById(String teacherId);

}
