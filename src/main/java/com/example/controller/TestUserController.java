package com.example.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.entity.TestUser;
import com.example.service.TestUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/testUser")
public class TestUserController {

    @Autowired
    private TestUserService testUserService;

    @RequestMapping("/getUsers")
    @ResponseBody
    public String getTestUserList(){
        List<TestUser> list = testUserService.selectAll();
        return JSON.toJSONString(list);
    }

    @RequestMapping("/saveUser")
    @ResponseBody
    public int saveUser(TestUser testUser){
        return testUserService.insertSelective(testUser);
    }

    @RequestMapping("/editUser")
    @ResponseBody
    public int editUser(TestUser testUser){
        return testUserService.updateByPrimaryKeySelective(testUser);
    }

    @RequestMapping("/destroyUser")
    @ResponseBody
    public Map destroyUser(Integer id){
//        System.out.println(id);
        Map map = new HashMap();

        int i =  testUserService.deleteByPrimaryKey(id);
        if(i> 0){
            map.put("success", true);
        }
        return map;
    }

}
