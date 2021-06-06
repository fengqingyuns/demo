package com.example.demo.activity.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.example.demo.activity.entity.RocIdUserRole;

@Mapper
public interface RocIdUserRoleMapper {

	 List<RocIdUserRole> selectByUserId(String userId);
}
