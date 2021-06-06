package com.example.demo.shiro.dao;

import org.apache.ibatis.annotations.Mapper;
import com.example.demo.activity.entity.RocIdUser;
import com.example.demo.shiro.entity.UserToken;

@Mapper
public interface UserTokenDao {

 String createToken(String userId);
    
    UserToken selectById(String userId);
    
    void insert(UserToken userToken);
    
    void updateById(UserToken userToken);
    
    UserToken queryByToken(String token);
    
    RocIdUser queryById(String userId);
}
