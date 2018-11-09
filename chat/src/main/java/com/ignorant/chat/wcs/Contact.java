package com.ignorant.chat.wcs;

public class Contact {

	private String UserName;

	private String NickName;

	private String HeadImgUrl;

	private String Sex;

	private String Signature;

	private String City;

	private int Status;

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getNickName() {
		return NickName;
	}

	public void setNickName(String nickName) {
		NickName = nickName;
	}

	public String getHeadImgUrl() {
		return HeadImgUrl;
	}

	public void setHeadImgUrl(String headImgUrl) {
		HeadImgUrl = headImgUrl;
	}

	public String getSex() {
		return Sex;
	}

	public void setSex(String sex) {
		Sex = sex;
	}

	public String getSignature() {
		return Signature;
	}

	public void setSignature(String signature) {
		Signature = signature;
	}

	public String getCity() {
		return City;
	}

	public void setCity(String city) {
		City = city;
	}

	public int getStatus() {
		return Status;
	}

	public void setStatus(int status) {
		Status = status;
	}

}
