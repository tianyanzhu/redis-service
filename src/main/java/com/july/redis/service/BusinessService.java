package com.july.redis.service;

import com.alibaba.fastjson.JSONObject;
import com.july.redis.vo.StudentInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 @author  july
 * 测试
 */
 // @Component
public class BusinessService extends RedisBaseService {
    public  String KEY_PRE="TEST";

    @Autowired
    RedisTemplate redisTemplate;
    /**
     * 启动时执行一次，之后每隔5秒执行一次
     */
   //@Scheduled(fixedRate = 1000 * 5)
    public void print() {
        System.out.println("timer running...");
        System.out.println(KEY_PRE);

        testObj();
    }
    private void testObj(){
        List<StudentInfo> infoList = new ArrayList<StudentInfo>();
        StudentInfo studentInfo1 = new StudentInfo();
        studentInfo1.setId("232424123");
        studentInfo1.setName("张珊");
        redisTemplate.opsForValue().set("232424123", studentInfo1, 10, TimeUnit.HOURS);
        StudentInfo vo = (StudentInfo)redisTemplate.opsForValue().get("232424123");
        System.out.println(JSONObject.toJSONString(vo));
    }

    private  void testList(){
        List<StudentInfo> infoList = new ArrayList<StudentInfo>();
        StudentInfo studentInfo1 = new StudentInfo();
        studentInfo1.setId("232424");
        studentInfo1.setName("张珊");

        redisTemplate.opsForValue().set("232424", studentInfo1, 10, TimeUnit.HOURS);
        System.out.println("==========" + JSONObject.toJSONString(redisTemplate.opsForValue().get("232424")));




        StudentInfo studentInfo2 = new StudentInfo();
        studentInfo2.setId("233434");
        studentInfo2.setName("张珊1");

        StudentInfo studentInfo3 = new StudentInfo();
        studentInfo3.setId("232424");
        studentInfo3.setName("张萌");

        StudentInfo studentInfo4 = new StudentInfo();
        studentInfo4.setId("1212323");
        studentInfo4.setName("俄格");

        StudentInfo studentInfo5 = new StudentInfo();
        studentInfo5.setId("1111");
        studentInfo5.setName("张3333珊");

        StudentInfo studentInfo6 = new StudentInfo();
        studentInfo6.setId("55555555555");
        studentInfo6.setName("地方");
        infoList.add(studentInfo1);
        infoList.add(studentInfo2);
        infoList.add(studentInfo3);
        infoList.add(studentInfo4);
        infoList.add(studentInfo5);
        infoList.add(studentInfo6);
        //可以
        String key = "student" + KEY_SPLIT +System.currentTimeMillis();
        redisTemplate.opsForValue().set(key, infoList, 10, TimeUnit.HOURS);
        List<StudentInfo> list= (List) redisTemplate.opsForValue().get(key);
        for(StudentInfo studentInfo: list){
            System.out.println("list集合=="+JSONObject.toJSONString(studentInfo));
        }


        redisTemplate.opsForValue().set(key, JSONObject.toJSONString(infoList), 10, TimeUnit.HOURS);
        String json = (String) redisTemplate.opsForValue().get(key);
        List jsonRe = JSONObject.parseObject(json,List.class);
        if (jsonRe != null  ) {
            for (Object o : jsonRe) {
            System.out.println(" 测试====="+JSONObject.toJSONString(o));
            }
        }

    }

    private void testSimpleList(){
        List<String> list = new ArrayList<String>();
        list.add("001");
        list.add("002");
        list.add("003");
        list.add("004");
        list.add("005");

        //可以
        redisTemplate.opsForValue().set("teacherId" + KEY_SPLIT + "1", list);
        System.out.println("obj=====" + JSONObject.toJSONString(  redisTemplate.opsForValue().get("teacherId" + KEY_SPLIT + "1")));

        // 这个可以
        redisTemplate.opsForValue().set("list1" + KEY_SPLIT + "4", list, 12, TimeUnit.HOURS);
//取出来的值看不懂
        List<String> result= (List<String>) redisTemplate.opsForValue().get("list1" + KEY_SPLIT + "4");
        System.out.println("list的方式 == "+JSONObject.toJSONString(result));
        for(int i=0;i<result.size();i++){
            System.out.println("++++++++==="+JSONObject.toJSONString(result.get(i)));
        }
    }

    private void testSet(){
        //可以
        Set<String> set = new HashSet<String>();
        set.add("占三");
        set.add("李四");
        set.add("王五");
        set.add("老刘");
        set.add("老气");
        set.add("老八");
        String key = "student33" + KEY_SPLIT + "33";
        redisTemplate.opsForSet().add(key, set);
        Set set1=redisTemplate.opsForSet().members(key);
        System.out.println("set方式=" + JSONObject.toJSONString(set1)) ;

        redisTemplate.opsForSet().add("student4" + KEY_SPLIT + "4",set);
        System.out.println("set1方式=" + JSONObject.toJSONString(redisTemplate.opsForSet().members("student4" + KEY_SPLIT + "4"))) ;
    }

    private  void testMap(){
        List<String> list = new ArrayList<String>();
        list.add("1qqqq");
        list.add("22tttt");
        list.add("0eeee03");
        list.add("rrrr");
        list.add("7775fgggg");
        //可以
        Map<String,List> map = new HashMap<String,List>();
        map.put("a1", list);
        map.put("b2", list);
        map.put("c3", list);
        map.put("d4", list);
        map.put("e5", list);
        redisTemplate.opsForHash().putAll("myId" + KEY_SPLIT + "22", map);
       // System.out.println("map方式=" + JSONObject.toJSONString(redisTemplate.opsForHash().entries("myId" + KEY_SPLIT + "2")));


        String key ="myId" + KEY_SPLIT  +System.currentTimeMillis();
        redisTemplate.opsForValue().set(key, map);
        System.out.println(JSONObject.toJSONString(redisTemplate.opsForValue().get(key)));

    }

    private void testeasy(){
        String key = "cache"+System.currentTimeMillis();
/*

        redisTemplate.opsForValue().set("key", "value");
        redisTemplate.opsForValue().get("key");


        redisTemplate.opsForList().leftPush(key, "aaa");
        redisTemplate.opsForList().leftPush(key, "bbb");
        redisTemplate.opsForList().leftPush(key, "ccc");
        System.out.println(JSONObject.toJSONString(redisTemplate.opsForList().range(key,0,0)));

*/

        key = "cache"+System.currentTimeMillis();
        List<String> list = new ArrayList<>();
        list.add("11111");
        list.add("33sfsd");
        list.add("43545");
        list.add("5465");
        list.add("5767");
        list.add("7878787");
        redisTemplate.opsForValue().set(key, list,10, TimeUnit.HOURS);
        System.out.println(JSONObject.toJSONString(redisTemplate.opsForValue().get(key)));

        /*redisTemplate.opsForSet().add(key, 111);
        redisTemplate.opsForSet().add(key,222);
        redisTemplate.opsForSet().add(key,333);
        redisTemplate.opsForSet().add(key,444);
        System.out.println(JSONObject.toJSONString(redisTemplate.opsForSet().members(key)));
        System.out.println(JSONObject.toJSONString(redisTemplate.opsForSet().randomMember(key)));*/
    }
}
