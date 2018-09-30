package com.asiabill.shiro.domain;

import java.io.Serializable;
import java.util.List;

public class SysRole implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id; // 编号
    private String role; // 角色标识程序中判断使用,如"admin",这个是唯一的:
    private String description; // 角色描述,UI界面显示使用
    private Integer available = 0;
    
    private List<SysPermission> permissions;//角色
    
    private List<UserInfo> userInfos;//用户
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
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
	public Integer getAvailable() {
		return available;
	}
	public void setAvailable(Integer available) {
		this.available = available;
	}
	public List<SysPermission> getPermissions() {
		return permissions;
	}
	public void setPermissions(List<SysPermission> permissions) {
		this.permissions = permissions;
	}
	public List<UserInfo> getUserInfos() {
		return userInfos;
	}
	public void setUserInfos(List<UserInfo> userInfos) {
		this.userInfos = userInfos;
	}
	@Override
	public String toString() {
		return "SysRole [id=" + id + ", role=" + role + ", description=" + description + ", available=" + available
				+ "]";
	}

}
