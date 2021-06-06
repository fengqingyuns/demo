/**
 * Copyright &copy; Edwin All rights reserved.
 */
package com.example.demo.activity.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.activiti.engine.impl.persistence.entity.GroupEntity;
import org.activiti.engine.impl.persistence.entity.UserEntity;

import com.example.demo.activity.entity.RocIdUser;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 流程工具
 * @author Edwin
 * @version 2016-11-03
 */
public class ActUtils {

//	private static Logger logger = LoggerFactory.getLogger(ActUtils.class);
	
	/**
	 * 定义流程定义KEY，必须以“PD_”开头
	 * 组成结构：string[]{"流程标识","业务主表表名"}
	 */
	public static final String[] PD_LEAVE = new String[]{"leave", "oa_leave"};
	public static final String[] PD_TEST_AUDIT = new String[]{"test_audit", "oa_test_audit"};
	public static final String[] PD_EXPENSE_ACCOUNT = new String[]{"expense_account", "oa_expense_account"};//网上报销流程
	public static final String[] PD_NETWORK_SERVICE = new String[]{"network_service", "oa_network_service"};//网络报修流程
//	/**
//	 * 流程定义Map（自动初始化）
//	 */
//	private static Map<String, String> procDefMap = new HashMap<String, String>() {
//		private static final long serialVersionUID = 1L;
//		{
//			for (Field field : ActUtils.class.getFields()){
//				if(StringUtils.startsWith(field.getName(), "PD_")){
//					try{
//						String[] ss = (String[])field.get(null);
//						put(ss[0], ss[1]);
//					}catch (Exception e) {
//						logger.debug("load pd error: {}", field.getName());
//					}
//				}
//			}
//		}
//	};
//	
//	/**
//	 * 获取流程执行（办理）URL
//	 * @param procId
//	 * @return
//	 */
//	public static String getProcExeUrl(String procId) {
//		String url = procDefMap.get(StringUtils.split(procId, ":")[0]);
//		if (StringUtils.isBlank(url)){
//			return "404";
//		}
//		return url;
//	}
	/**
	 * 转换流程节点类型为中文说明
	 * @param type 英文名称
	 * @return 翻译后的中文名称
	 */
	public static String parseToZhType(String type) {
		Map<String, String> types = new HashMap<String, String>();
		types.put("userTask", "用户任务");
		types.put("serviceTask", "系统任务");
		types.put("startEvent", "开始节点");
		types.put("endEvent", "结束节点");
		types.put("exclusiveGateway", "条件判断节点(系统自动根据条件处理)");
		types.put("inclusiveGateway", "并行处理任务");
		types.put("callActivity", "子流程");
		return types.get(type) == null ? type : types.get(type);
	}
	
	
	
	
	public static UserEntity toActivitiUser(RocIdUser user){
		if (user == null){
			return null;
		}
		UserEntity userEntity = new UserEntity();
		userEntity.setId(user.getId());
		userEntity.setFirstName(user.getUserName());
		userEntity.setLastName(StringUtils.EMPTY);
		userEntity.setPassword(user.getPassWord());
		userEntity.setRevision(1);
		return userEntity;
	}
}
