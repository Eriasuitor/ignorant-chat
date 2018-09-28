package com.ignorant.chat.domain;

import java.io.Serializable;
import java.util.List;

public class UserEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private String passWord;
	private String salt;
	private byte state;
	private List<RoleEntity> roleList;

	public UserEntity() {
		super();
	}

	public UserEntity(String name, String passWord, String salt, byte state) {
		super();
		this.name = name;
		this.passWord = passWord;
		this.salt = salt;
		this.state = state;
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

	public String getPassWord() {
		return passWord;
	}

	public void setPasswWrd(String passWord) {
		this.passWord = passWord;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public byte getState() {
		return state;
	}

	public void setState(byte state) {
		this.state = state;
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
