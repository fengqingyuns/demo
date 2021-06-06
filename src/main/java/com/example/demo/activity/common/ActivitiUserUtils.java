package com.example.demo.activity.common;

import java.util.ArrayList;
import java.util.List;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.activiti.engine.impl.persistence.entity.GroupEntity;
import org.activiti.engine.impl.persistence.entity.UserEntity;

import com.example.demo.activity.entity.RocIdRole;
import com.example.demo.activity.entity.RocIdUser;



/**
 * 将业务中自己定义的用户、角色转化为activiti中使用的user、group
 */
public class ActivitiUserUtils {
    public static User toActivitiUser(RocIdUser bUser){
        User userEntity=new UserEntity();
        userEntity.setId(bUser.getUserName());
        userEntity.setFirstName(bUser.getNickName());
        userEntity.setLastName(bUser.getNickName());
        userEntity.setPassword(bUser.getPassWord());
        return userEntity;
    }
    public static GroupEntity toActivitiGroup(RocIdRole sysRole){
        GroupEntity groupEntity=new GroupEntity();
        groupEntity.setRevision(1);
        groupEntity.setType("assignment");
        groupEntity.setId(sysRole.getRole());
        groupEntity.setName(sysRole.getRole());
        return groupEntity;
    }
    public static List<Group> toActivitiGroups(List<RocIdRole> sysRoles){
        List<Group> groups=new ArrayList<Group>();
        for (RocIdRole role:sysRoles
             ) {
            GroupEntity groupEntity=toActivitiGroup(role);
            groups.add(groupEntity);
        }
        return groups;
    }
}
