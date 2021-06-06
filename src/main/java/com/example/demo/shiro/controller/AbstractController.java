package com.example.demo.shiro.controller;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.demo.activity.entity.RocIdUser;


public abstract class AbstractController {

    protected Logger logger = LoggerFactory.getLogger(getClass());
    
    protected RocIdUser getUser() {
        return (RocIdUser) SecurityUtils.getSubject().getPrincipal();
    }
    protected String getUserId() {
        return getUser().getId();
    }
}
