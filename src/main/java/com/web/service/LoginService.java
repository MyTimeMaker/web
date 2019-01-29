package com.web.service;

import com.web.entity.User;
import com.web.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    private UserMapper userMapper;
    public User getUserInfo(String username,String password){
        User user=userMapper.queryUserInfo(username,password);
        return user;
    }
}
