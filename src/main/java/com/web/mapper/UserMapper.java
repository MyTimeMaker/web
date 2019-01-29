package com.web.mapper;

import com.web.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    User queryUserInfo(String username,String password);
}
