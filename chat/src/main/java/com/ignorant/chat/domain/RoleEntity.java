package com.ignorant.chat.domain;

import java.io.Serializable;
import java.util.List;

public class RoleEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private String descriptin;
	private Boolean available;
	private List<PermissionEntity> permissionList;
	private List<UserEntity> userList;

	public RoleEntity() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescriptin() {
		return descriptin;
	}

	public void setDescriptin(String descriptin) {
		this.descriptin = descriptin;
	}

	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}

	public List<PermissionEntity> getPermissionList() {
		return permissionList;
	}

	public void setPermissionList(List<PermissionEntity> permissionList) {
		this.permissionList = permissionList;
	}

	public List<UserEntity> getUserList() {
		return userList;
	}

	public void setUserList(List<UserEntity> userList) {
		this.userList = userList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
