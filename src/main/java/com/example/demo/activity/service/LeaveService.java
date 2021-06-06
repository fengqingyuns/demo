package com.example.demo.activity.service;

import org.activiti.engine.runtime.ProcessInstance;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.activity.entity.LeaveApply;
import com.github.pagehelper.PageInfo;

public interface LeaveService extends IService<LeaveApply>{

	ProcessInstance startLeave(LeaveApply leave);
	
	PageInfo getTask(String userId);
}
