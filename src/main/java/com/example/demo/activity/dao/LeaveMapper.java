package com.example.demo.activity.dao;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.activity.entity.LeaveApply;

@Mapper
public interface LeaveMapper extends BaseMapper<LeaveApply>{

	
}
