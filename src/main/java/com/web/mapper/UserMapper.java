package com.web.mapper;

import com.web.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    User queryUserInfo(@Param("username") String username, @Param("password") String password);
}
