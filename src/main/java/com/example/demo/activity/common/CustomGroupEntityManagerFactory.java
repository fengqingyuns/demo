package com.example.demo.activity.common;

import javax.annotation.Resource;

import org.activiti.engine.impl.interceptor.Session;
import org.activiti.engine.impl.interceptor.SessionFactory;
import org.activiti.engine.impl.persistence.entity.GroupIdentityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 角色类的管理
 */
@Service
public class CustomGroupEntityManagerFactory implements SessionFactory{
    @Resource
    private CustomGroupEntityManager customGroupEntityManager;

    @Override
    public Class<?> getSessionType() {
        //返回原始的groupmanager类型
        return GroupIdentityManager.class;
    }

    @Override
    public Session openSession() {
        //返回自定义的GroupManager实例
        return customGroupEntityManager;
    }

    @Autowired
    public void setCustomGroupEntityManager(CustomGroupEntityManager customGroupEntityManager){
        this.customGroupEntityManager=customGroupEntityManager;
    }
}
