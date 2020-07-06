package com.example.mapper;

import com.example.entity.TestUser;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("testUserMapper")
public interface TestUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TestUser record);

    int insertSelective(TestUser record);

    TestUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TestUser record);

    int updateByPrimaryKey(TestUser record);

    List<TestUser> selectAll();
}