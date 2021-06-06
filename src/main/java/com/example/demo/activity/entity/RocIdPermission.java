package com.example.demo.activity.entity;

public class RocIdPermission {

	private Integer id;
	private Integer pid;
	private String name;
	private boolean open = true;
	private boolean checked = false;
	private String icon;
	private String url;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isOpen() {
		return open;
	}
	public void setOpen(boolean open) {
		this.open = open;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public String toString() {
		return "RocIdPermission [id=" + id + ", pid=" + pid + ", name=" + name + ", open=" + open + ", checked="
				+ checked + ", icon=" + icon + ", url=" + url + "]";
	}
	
	
}
