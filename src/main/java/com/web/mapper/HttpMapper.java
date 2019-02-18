package com.web.mapper;

import com.web.entity.AccessToken;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface HttpMapper {
    void insertAccessToken(@Param("access_token")String access_token,@Param("expires_in")int expires_in);
    AccessToken getAccessToken();
    void deleteAccessToken();
}
