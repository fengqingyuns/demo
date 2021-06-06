package com.example.demo.activity.controller;

import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.activity.entity.LeaveApply;
import com.example.demo.activity.service.LeaveService;
import com.example.demo.activity.util.PageResult;
import com.example.demo.base.controller.AbstractController;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/leave")
public class LeaveController extends AbstractController{

	@Autowired
	private LeaveService leaveService;

	/**
	 * 启动请假流程
	 * @param leave
	 * @return
	 */
	@RequestMapping("/start")
	@ResponseBody
	public String startLeave(@RequestBody LeaveApply leave) {
		
		ProcessInstance startLeave = leaveService.startLeave(leave);
		return startLeave.toString();
	}
	/**
	 * 获取部门领导审批代办列表
	 * @param userId
	 * @return
	 */
	@RequestMapping("/querytask")
	@ResponseBody
	public PageResult queryTask() {
		String userId = getUserId();
		PageInfo pageInfo = leaveService.getTask(userId);
		
		return PageResult.pageResult(pageInfo);
	}
	
	
	
	
	
}
