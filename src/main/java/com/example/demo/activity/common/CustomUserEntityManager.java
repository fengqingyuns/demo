package com.example.demo.activity.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.activiti.engine.impl.Page;
import org.activiti.engine.impl.UserQueryImpl;
import org.activiti.engine.impl.persistence.entity.IdentityInfoEntity;
import org.activiti.engine.impl.persistence.entity.UserEntity;
import org.activiti.engine.impl.persistence.entity.UserEntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.activity.contents.WorkflowConstants;
import com.example.demo.activity.dao.RocIdRoleMapper;
import com.example.demo.activity.dao.RocIdUserMapper;
import com.example.demo.activity.dao.RocIdUserRoleMapper;
import com.example.demo.activity.entity.RocIdRole;
import com.example.demo.activity.entity.RocIdUser;
import com.example.demo.activity.entity.RocIdUserRole;

/**
 * 自定义用户管理类，管理用户方法
 * 添加其他方法
 */
@Component
public class CustomUserEntityManager extends UserEntityManager{
    @Autowired
    private RocIdUserMapper rocIdUserMapper;
    @Autowired
    private RocIdRoleMapper rocIdRoleMapper;
    @Autowired
    private RocIdUserRoleMapper rocIdUserRoleMapper;
    @Override
    public User findUserById(String userId){
        User userEntity=new UserEntity();
        RocIdUser  rocIdUser=rocIdUserMapper.selectById(Integer.parseInt(userId));
        //将自定义的user转化为activiti的类
        userEntity=ActivitiUserUtils.toActivitiUser(rocIdUser);
        //返回activiti的实体类
        return userEntity;
    }

    @Override
    public List<Group> findGroupsByUser(final String userId) {
        if(userId==null){
            return null;
        }
        List<RocIdRole> roleList=new ArrayList<RocIdRole>();
        List<RocIdUserRole> userRoleList=rocIdUserRoleMapper.selectByUserId(userId);
        for (RocIdUserRole userrole:userRoleList
             ) {
            String roleId=userrole.getRoleId();
            RocIdRole role=rocIdRoleMapper.selectById(Integer.parseInt(roleId));
            roleList.add(role);
        }
        List<Group> gs=null;
        gs=ActivitiUserUtils.toActivitiGroups(roleList);
        return gs;
    }
    @Override
    public List<org.activiti.engine.identity.User> findUserByQueryCriteria(UserQueryImpl query, Page page) {
        RocIdUser user = getUser(query.getId());
        List<org.activiti.engine.identity.User> list = new ArrayList<>();
        list.add(ActivitiUserUtils.toActivitiUser(user));
        return list;
    }
    
    private RocIdUser getUser(String userId) {
        RocIdUser user = new RocIdUser();
        if(WorkflowConstants.INTERFACE_SYSTEM_ID.equals(userId)) {
            //user.setPkid(Long.valueOf(WorkflowConstants.INTERFACE_SYSTEM_ID));
            user.setId(userId);
            user.setNickName(WorkflowConstants.INTERFACE_SYSTEM_NAME);
            user.setNickName(WorkflowConstants.INTERFACE_SYSTEM_NAME);
            user.setPassWord("123456");
            
        }else {
            RocIdUser prarmUser = new RocIdUser();
            prarmUser.setId(userId);
            user = rocIdUserMapper.selectById(userId);
        }
        return user;
    }

    @Override
    public long findUserCountByQueryCriteria(UserQueryImpl query) {
        throw new RuntimeException("not implement method.");
    }

    @Override
    public IdentityInfoEntity findUserInfoByUserIdAndKey(String userId, String key) {
        throw new RuntimeException("not implement method.");
    }

    @Override
    public List<String> findUserInfoKeysByUserIdAndType(String userId, String type) {
        throw new RuntimeException("not implement method.");
    }

    @Override
    public List<org.activiti.engine.identity.User> findPotentialStarterUsers(String proceDefId) {
        throw new RuntimeException("not implement method.");
    }

    @Override
    public List<org.activiti.engine.identity.User> findUsersByNativeQuery(Map<String, Object> parameterMap,
            int firstResult, int maxResults) {
        throw new RuntimeException("not implement method.");
    }

    @Override
    public long findUserCountByNativeQuery(Map<String, Object> parameterMap) {
        throw new RuntimeException("not implement method.");
    }
    
}