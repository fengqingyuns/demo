package com.example.demo.shiro.service.impl;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.shiro.dao.UserTokenDao;
import com.example.demo.shiro.entity.UserToken;
import com.example.demo.shiro.oauth.TokenGenerator;
import com.example.demo.shiro.service.UserTokenService;

@Service
public class UserTokenServiceImpl implements UserTokenService{
  //12小时后过期
    private final static Long EXPIRE = (long) (3600 * 12);
    
    @Autowired
    private UserTokenDao userTokenDao;
    @Override
    public String createToken(String userId) {
        // TODO Auto-generated method stub
        
      //生成一个token
        String token = TokenGenerator.generateValue();
      //当前时间
        Date now = new Date();
        //过期时间
        Date expireTime = new Date(now.getTime() + EXPIRE * 1000);
      //判断是否生成过token
        UserToken tokenEntity = this.selectById(userId);
        if(tokenEntity == null){
            tokenEntity = new UserToken();
            tokenEntity.setUserId(userId);
            tokenEntity.setToken(token);
            tokenEntity.setUpdateTime(now);
            tokenEntity.setExpireTime(expireTime);

            //保存token
            this.insert(tokenEntity);
        }else{
            tokenEntity.setToken(token);
            tokenEntity.setUpdateTime(now);
            tokenEntity.setExpireTime(expireTime);

            //更新token
            this.updateById(tokenEntity);
        }

      //  R r = R.ok().put("token", token).put("expire", EXPIRE);

        return tokenEntity.getToken();
    }
    @Override
    public UserToken selectById(String userId) {
        UserToken dbuser = userTokenDao.selectById(userId);
        // TODO Auto-generated method stub
        return dbuser;
    }
    @Override
    public void insert(UserToken userToken) {
        // TODO Auto-generated method stub
        userTokenDao.insert(userToken);
    }
    @Override
    public void updateById(UserToken userToken) {
        // TODO Auto-generated method stub
        userTokenDao.updateById(userToken);
    }

}
