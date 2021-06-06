/**
 * Copyright &copy; Edwin All rights reserved.
 */
package com.example.demo.activity.entity;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Activiti Entity类
 * @author Edwin
 * @version 2016-05-28
 */
public abstract class ActEntity<T>  implements Serializable {

	private static final long serialVersionUID = 1L;

	protected Act act; 		// 流程任务对象

	private RocIdUser user;	//	归属用户

	private Office office;	//	归属部门

	private String 	content;	//	调整原因

	private String businessTable;


	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public RocIdUser getUser() {
		return user;
	}

	public void setUser(RocIdUser user) {
		this.user = user;
	}

	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}
	
	@JsonIgnore
	public Act getAct() {
		if (act == null){
			act = new Act();
		}
		return act;
	}

	public void setAct(Act act) {
		this.act = act;
	}

	/**
	 * 获取流程实例ID
	 * @return
	 */
	public String getProcInsId() {
		return this.getAct().getProcInsId();
	}

	/**
	 * 设置流程实例ID
	 * @param procInsId
	 */
	public void setProcInsId(String procInsId) {
		this.getAct().setProcInsId(procInsId);
	}

	public String getBusinessTable() {
		return businessTable;
	}

	public void setBusinessTable(String businessTable) {
		this.businessTable = businessTable;
	}
}
