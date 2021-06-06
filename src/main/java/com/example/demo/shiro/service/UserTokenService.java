package com.example.demo.shiro.service;

import com.example.demo.shiro.entity.UserToken;

public interface UserTokenService {

    String createToken(String userId);
    
    UserToken selectById(String userId);
    
    void insert(UserToken userToken);
    
    void updateById(UserToken userToken);
}
