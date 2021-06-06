package com.example.demo.activity.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.activity.entity.RocIdUser;
@Mapper
public interface RocIdUserMapper  extends BaseMapper<RocIdUser>{

	RocIdUser selectById(String userId);
	
	List<String> selectPermissionsByUserId(String userId);
	
}
