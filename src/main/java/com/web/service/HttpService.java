package com.web.service;

import com.web.mapper.HttpMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HttpService {
    @Autowired
    private HttpMapper httpMapper;
    public void insertAccessToken(String access_token,int expires_in){
        httpMapper.insertAccessToken(access_token,expires_in);
    }
}

