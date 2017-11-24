package com.july.test;

import com.alibaba.fastjson.JSONObject;
import com.july.redis.RedisCacheServices;
import com.july.redis.vo.StudentInfo;
import junit.framework.TestCase;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by july on 2017/11/10.
 */
public class RedisServiceTest extends TestCase {

    @org.junit.Test
    public void testFindbyId() throws Exception {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"consumer.xml"});
        context.start();

        RedisCacheServices redisCacheServices = (RedisCacheServices)context.getBean("redisCacheServices"); // 获取远程服务代理
        StudentInfo studentInfo= redisCacheServices.getStudentInfo("232424123");
        System.out.println(JSONObject.toJSONString(studentInfo));
    }
}
