package com.example.service;

import com.example.entity.TestUser;
import com.example.entity.User;

import java.util.List;

public interface TestUserService {
    int deleteByPrimaryKey(Integer id);

    int insert(TestUser record);

    int insertSelective(TestUser record);

    TestUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TestUser record);

    int updateByPrimaryKey(TestUser record);

    List<TestUser> selectAll();
}
