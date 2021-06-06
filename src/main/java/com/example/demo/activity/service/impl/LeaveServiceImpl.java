package com.example.demo.activity.service.impl;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.IdentityService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.activity.dao.LeaveMapper;
import com.example.demo.activity.entity.LeaveApply;
import com.example.demo.activity.entity.RocIdRole;
import com.example.demo.activity.entity.RocIdUser;
import com.example.demo.activity.service.LeaveService;
import com.example.demo.activity.service.SystemService;
import com.example.demo.activity.util.DateConverter;
import com.github.pagehelper.PageInfo;

@Service
public class LeaveServiceImpl extends ServiceImpl<LeaveMapper, LeaveApply> implements LeaveService{

	@Autowired
	private LeaveMapper leaveMapper;
	@Autowired
	private IdentityService identityservice;
	@Autowired
	private RuntimeService runtimeservice;
	@Autowired
	private SystemService systemService;
	
	@Override
	public ProcessInstance startLeave(LeaveApply leave) {
		String start_time = leave.getReality_start_time();
		String end_time = leave.getReality_end_time();
		Long days = 0l;
		try {
			 days = DateConverter.days(end_time, start_time);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 leave.setDays(days);
		Map<String,Object> variables=new HashMap<String, Object>();
	//	variables.put("message", true);
		leaveMapper.insert(leave);
		String businesskey=String.valueOf(leave.getId());//使用leaveapply表的主键作为businesskey,连接业务数据和流程数据
		identityservice.setAuthenticatedUserId(leave.getUser_id());
		ProcessInstance instance=runtimeservice.startProcessInstanceByKey(leave.getProcess_instance_id(),businesskey,variables);
		System.err.println(instance);
		return instance;
	}
	@Override
	public PageInfo getTask(String userId) {
		// TODO Auto-generated method stub
		RocIdUser byUserId = systemService.getUserByUserId(userId);
		List<RocIdRole> role = byUserId.getRocIdRole();
		if(role != null && role.size() > 0) {
			
		}
		return null;
	}

}
