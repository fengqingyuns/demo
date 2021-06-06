package com.example.demo.shiro.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.activity.dao.MasterDao;
import com.example.demo.activity.entity.Menu;
import com.example.demo.activity.entity.RocIdUser;
import com.example.demo.shiro.entity.UserToken;
import com.example.demo.shiro.service.ShiroService;
import com.example.demo.shiro.dao.MenuDao;
import com.example.demo.shiro.dao.UserTokenDao;
@Service
public class ShiroServiceImpl implements ShiroService{
    private static final Logger LOGGER = LoggerFactory.getLogger(ShiroServiceImpl.class);
    @Autowired
    private UserTokenDao UserTokenDao;
    @Autowired
    private MenuDao menuDao;
    @Autowired
    private MasterDao masterDao;
    @Override
    public Set<String> getUserPermissions(String userId) {
            List<String> permsList;
        //判断是否是系统管理员
        if(userId == "1") {
            List<Menu> menuList = menuDao.selectList(null);
            permsList = new ArrayList<>(menuList.size());
            permsList.addAll(menuList.stream().map(Menu::getPerms).collect(Collectors.toList()));
        }else{
            permsList = masterDao.queryAllPerms(userId);
            LOGGER.info("permsList", permsList);
        }
        //用户权限列表
        Set<String> permsSet = new HashSet<>();
        for (String perms : permsList) {
            if(StringUtils.isBlank(perms)) {
                continue;
            }
            permsSet.addAll(Arrays.asList(perms.trim().split(",")));
        }
        return permsSet;
    }

    @Override
    public UserToken queryByToken(String accessToken) {
        // TODO Auto-generated method stub
        UserToken userToken = UserTokenDao.queryByToken(accessToken);
        return userToken;
    }

    @Override
    public RocIdUser queryUser(String userId) {
        // TODO Auto-generated method stub
    	RocIdUser user = UserTokenDao.queryById(userId);
        return user;
    }

}
