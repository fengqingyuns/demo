package com.example.demo.activity.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.activiti.engine.identity.Group;
import org.activiti.engine.impl.GroupQueryImpl;
import org.activiti.engine.impl.Page;
import org.activiti.engine.impl.persistence.entity.GroupEntity;
import org.activiti.engine.impl.persistence.entity.GroupEntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.activity.dao.RocIdRoleMapper;
import com.example.demo.activity.dao.RocIdUserMapper;
import com.example.demo.activity.dao.RocIdUserRoleMapper;
import com.example.demo.activity.entity.RocIdRole;
import com.example.demo.activity.entity.RocIdUser;
import com.example.demo.activity.entity.RocIdUserRole;


/**
 * 自定义角色管理
 * 具体方法进入GroupEntityManager中查看
 */
@Component
public class CustomGroupEntityManager extends GroupEntityManager{
    @Autowired
    private RocIdUserMapper rocIdUserMapper;
    @Autowired
    private RocIdUserRoleMapper rocIdUserRoleMapper;
    @Autowired
    private RocIdRoleMapper rocIdRoleMapper;

    @Override
    public Group createNewGroup(String groupId) {
        return super.createNewGroup(groupId);
    }

    /**
     * 查找角色
     * @param userId
     * @return
     */
    @Override
    public List<Group> findGroupsByUser(final String userId) {
        if(userId==null){
            return null;
        }
        System.out.println("userId:"+userId);
        RocIdUser rocIdUser=rocIdUserMapper.selectById(Integer.parseInt(userId));
        List<RocIdUserRole> userRoleList=rocIdUserRoleMapper.selectByUserId(rocIdUser.getId());
        System.out.println("userRoleList size:"+userRoleList.size());
        List<Group> gs=new ArrayList<Group>();
        GroupEntity groupEntity;
        String roleId;
        String activitiRole;
        for (RocIdUserRole userRole:userRoleList
             ) {
            groupEntity=new GroupEntity();
            groupEntity.setRevision(1);
            groupEntity.setType("assignment");
            roleId=userRole.getRoleId();
            RocIdRole role=rocIdRoleMapper.selectById(Integer.parseInt(roleId));
            groupEntity.setId(role.getRole());
            groupEntity.setName(role.getRole());
            gs.add(groupEntity);
        }
        return gs;
    }

    @Override
    public List<Group> findGroupByQueryCriteria(GroupQueryImpl query, Page page) {
        throw new RuntimeException("not implement method.");
    }

    @Override
    public long findGroupCountByQueryCriteria(GroupQueryImpl query) {
        throw new RuntimeException("not implement method.");
    }

    @Override
    public List<Group> findGroupsByNativeQuery(Map<String, Object> parameterMap, int firstResult, int maxResults) {
        throw new RuntimeException("not implement method.");
    }

    @Override
    public long findGroupCountByNativeQuery(Map<String, Object> parameterMap) {
        throw new RuntimeException("not implement method.");
    }
}