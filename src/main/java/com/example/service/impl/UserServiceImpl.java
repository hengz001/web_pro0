package com.example.service.impl;

import com.example.entity.User;
import com.example.mapper.UserMapper;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    public int deleteByPrimaryKey(Integer id) {
        return userMapper.deleteByPrimaryKey(id);
    }

    public int insert(User record) {
        return userMapper.insert(record);
    }

    public int insertSelective(User record) {
        return userMapper.insertSelective( record);
    }

    public User selectByPrimaryKey(Integer id) {
        return userMapper.selectByPrimaryKey( id);
    }

    public int updateByPrimaryKeySelective(User record) {
        return userMapper.updateByPrimaryKeySelective( record);
    }

    public int updateByPrimaryKey(User record) {
        return userMapper.updateByPrimaryKey( record);
    }

    public User sel(Integer id) {
        return userMapper.sel( id);
    }
}
