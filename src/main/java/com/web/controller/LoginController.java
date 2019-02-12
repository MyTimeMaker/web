package com.web.controller;

import com.web.entity.User;
import com.web.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;
    @RequestMapping("/login")
    public User checkLogin(@RequestBody User user){
        User login_user=loginService.getUserInfo(user.getUsername(),user.getPassword());
        return login_user;
    }
}
