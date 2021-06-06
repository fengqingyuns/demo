package com.example.demo.activity.dao;

import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.activity.entity.Act;

/**
 * 审批Mapper接口
 * @author
 * @version 
 */
@Mapper
public interface ActMapper extends BaseMapper<Act>{

	public int updateProcInsIdByBusinessId(Act act);
	
}
