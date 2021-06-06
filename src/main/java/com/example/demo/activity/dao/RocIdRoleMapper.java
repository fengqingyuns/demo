package com.example.demo.activity.dao;

import org.apache.ibatis.annotations.Mapper;
import com.example.demo.activity.entity.RocIdRole;

@Mapper
public interface RocIdRoleMapper {

	   RocIdRole selectById(int roleId);
}
