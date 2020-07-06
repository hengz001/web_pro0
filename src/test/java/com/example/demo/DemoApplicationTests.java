package com.example.demo;

//import com.alibaba.fastjson.JSON;
import com.example.entity.TestUser;
import com.example.service.TestUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    TestUserService testUserService;

    @Test
    void contextLoads() {
//        TestUser testUser= testUserService.selectByPrimaryKey(1);
//        String szObj = JSON.toJSONString(testUser);
//        System.out.println(szObj);
        List<TestUser> list = testUserService.selectAll();
        System.out.println(list.size());
        System.out.println("done.");
    }
}
