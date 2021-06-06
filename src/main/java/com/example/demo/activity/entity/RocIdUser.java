package com.example.demo.activity.entity;

import java.util.List;


public class RocIdUser {

	private String id;
	private String userName;
	
	private String nickName;
	
	private String passWord;

	//加密盐值
	 private String salt;
	//状态信息
	 private Integer status;
	 
	 private List<RocIdRole> rocIdRole;
	 
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public List<RocIdRole> getRocIdRole() {
		return rocIdRole;
	}

	public void setRocIdRole(List<RocIdRole> rocIdRole) {
		this.rocIdRole = rocIdRole;
	}

	@Override
	public String toString() {
		return "RocIdUser [id=" + id + ", userName=" + userName + ", nickName=" + nickName + ", passWord=" + passWord
				+ ", salt=" + salt + ", status=" + status + ", rocIdRole=" + rocIdRole + "]";
	}

	
	
}
