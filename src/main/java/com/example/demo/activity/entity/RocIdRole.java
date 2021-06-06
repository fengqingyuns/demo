package com.example.demo.activity.entity;

import java.util.List;

public class RocIdRole {
	 private String id;
	    //角色名称
	    private String role;
	    //描述
	    private String description;
	    //是否可用
	    private Integer enable;
	    //权限信息
	    private List<RocIdPermission> permission;
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getRole() {
			return role;
		}
		public void setRole(String role) {
			this.role = role;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public Integer getEnable() {
			return enable;
		}
		public void setEnable(Integer enable) {
			this.enable = enable;
		}
		public List<RocIdPermission> getPermission() {
			return permission;
		}
		public void setPermission(List<RocIdPermission> permission) {
			this.permission = permission;
		}
		@Override
		public String toString() {
			return "RocIdRole [id=" + id + ", role=" + role + ", description=" + description + ", enable=" + enable
					+ ", permission=" + permission + "]";
		}
		
}
