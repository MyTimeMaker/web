package com.web.controller;

import com.web.entity.User;
import com.web.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;
    @RequestMapping("/login")
    public User checkLogin(){
        User user=loginService.getUserInfo("liujiawei","123456");
        return user;
    }
}
