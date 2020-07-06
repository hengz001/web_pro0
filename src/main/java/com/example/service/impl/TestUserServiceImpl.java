package com.example.service.impl;

import com.example.entity.TestUser;
import com.example.mapper.TestUserMapper;
import com.example.service.TestUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("testUserService")
public class TestUserServiceImpl implements TestUserService {

    @Autowired
    private TestUserMapper testUserMapper;

    public int deleteByPrimaryKey(Integer id){
        return testUserMapper.deleteByPrimaryKey(id);
    }

    public int insert(TestUser record){
        return testUserMapper.insert( record);
    }


    public int insertSelective(TestUser record){
        return testUserMapper.insertSelective( record);
    }

    public TestUser selectByPrimaryKey(Integer id){
        return testUserMapper.selectByPrimaryKey( id);
    }

    public int updateByPrimaryKeySelective(TestUser record){
        return testUserMapper.updateByPrimaryKeySelective( record);
    }

    public int updateByPrimaryKey(TestUser record){
        return testUserMapper.updateByPrimaryKey( record);
    }

    public List<TestUser> selectAll() {
        return testUserMapper.selectAll();
    }
}
