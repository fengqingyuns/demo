package com.example.demo.activity.entity;

public class RocRoleMenu {

	private int id;
	
	private String roleId;
	
	private int menuId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public int getMenuId() {
		return menuId;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

	@Override
	public String toString() {
		return "RocRoleMenu [id=" + id + ", roleId=" + roleId + ", menuId=" + menuId + "]";
	}
	
	
}
