package com.example.demo.shiro.service;

import java.util.Set;
import com.example.demo.activity.entity.RocIdUser;
import com.example.demo.shiro.entity.UserToken;

public interface ShiroService {
    Set<String> getUserPermissions(String userId);
    
    UserToken queryByToken(String accessToken);
    
    RocIdUser queryUser(String userId);
}
