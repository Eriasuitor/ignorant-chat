package com.ignorant.chat.domain;

import java.io.Serializable;
import java.util.List;

public class PermissionEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private String resourceType;
	private String url;
	private String permission;
	private Long parantId;
	private String parantIds;
	private Boolean available = Boolean.FALSE;
	private List<RoleEntity> roleList;

	public PermissionEntity() {
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

	public String getResourceType() {
		return resourceType;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public Long getParantId() {
		return parantId;
	}

	public void setParantId(Long parantId) {
		this.parantId = parantId;
	}

	public String getParantIds() {
		return parantIds;
	}

	public void setParantIds(String parantIds) {
		this.parantIds = parantIds;
	}

	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}

	public List<RoleEntity> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<RoleEntity> roleList) {
		this.roleList = roleList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
