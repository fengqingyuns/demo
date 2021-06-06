package com.example.demo.activity.entity;

public class RocIdUserRole {
	private Integer id;
	 //用户id
	 private String userId;
	 //角色id
	 private String roleId;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	@Override
	public String toString() {
		return "RocIdUserRole [id=" + id + ", userId=" + userId + ", roleId=" + roleId + "]";
	}
	 
}
