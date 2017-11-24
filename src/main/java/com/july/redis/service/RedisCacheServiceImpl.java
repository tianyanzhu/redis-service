package com.july.redis.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSONObject;
import com.july.redis.RedisCacheServices;
import com.july.redis.vo.StudentInfo;
import com.july.redis.vo.TeacherInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * 支持redis集群和单机两种模式下的实现方式
 * @author july
 */
@Service
class RedisCacheServiceImpl implements RedisCacheServices {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    RedisTemplate redisTemplate;
    /**
     * 一天的秒数
     */

    private static final int DAYS_MILLISECONDS = 1*24*60*60;

    private static final String KEY_SPLIT = ":";

    @Override
    public StudentInfo getStudentInfo(String userCodeOrId) {
        StudentInfo studentInfo = (StudentInfo) redisTemplate.opsForValue().get(RedisCacheServices.REDIS_STUDENT_CODE_KEY + KEY_SPLIT + userCodeOrId);
        if(null!=studentInfo){
            logger.info(userCodeOrId+"学生信息=="+JSONObject.toJSONString(studentInfo));
            return  studentInfo;
        }
        return null;

    }

    @Override
    public void saveStudentInfo(String userCodeOrId, StudentInfo studentInfo) {
        logger.info(userCodeOrId+"=="+JSONObject.toJSONString(studentInfo));
        redisTemplate.opsForValue().set(RedisCacheServices.REDIS_STUDENT_CODE_KEY + KEY_SPLIT + userCodeOrId, studentInfo, DAYS_MILLISECONDS,TimeUnit.MILLISECONDS);
    }

    @Override
    public void saveTeacherInfo(String teacherId, TeacherInfo teacherInfo) {
        logger.info(teacherId + "==" + JSONObject.toJSONString(teacherInfo));
        redisTemplate.opsForValue().set(RedisCacheServices.REDIS_TEACHER_ID_KEY + KEY_SPLIT + teacherId, teacherInfo, DAYS_MILLISECONDS,TimeUnit.MILLISECONDS);
    }

    @Override
    public TeacherInfo getTeacherInfoById(String teacherId) {
        TeacherInfo teacherInfo = (TeacherInfo) redisTemplate.opsForValue().get(RedisCacheServices.REDIS_TEACHER_ID_KEY + KEY_SPLIT + teacherId);
        if(null!=teacherInfo){
            logger.info(teacherId+"获取教师信息=="+JSONObject.toJSONString(teacherInfo));
            return teacherInfo;
        }
        return null;
    }

}
