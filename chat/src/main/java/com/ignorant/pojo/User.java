package com.ignorant.pojo;

import java.util.Date;
import javax.persistence.*;

public class User {
    @Id
    private String userId;

    private String nickName;

    private String gender;

    private Byte age;

    private String avatar;

    private String avatar_small;

    private String city;

    private Date createDate;

    private Date lastEditData;

    private String createUser;

    private String lastEditUser;

    /**
     * @return userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return nickName
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * @param nickName
     */
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    /**
     * @return gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * @return age
     */
    public Byte getAge() {
        return age;
    }

    /**
     * @param age
     */
    public void setAge(Byte age) {
        this.age = age;
    }

    /**
     * @return avatar
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * @param avatar
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    /**
     * @return avatar_small
     */
    public String getAvatar_small() {
        return avatar_small;
    }

    /**
     * @param avatar_small
     */
    public void setAvatar_small(String avatar_small) {
        this.avatar_small = avatar_small;
    }

    /**
     * @return city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return createDate
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * @param createDate
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * @return lastEditData
     */
    public Date getLastEditData() {
        return lastEditData;
    }

    /**
     * @param lastEditData
     */
    public void setLastEditData(Date lastEditData) {
        this.lastEditData = lastEditData;
    }

    /**
     * @return createUser
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * @param createUser
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    /**
     * @return lastEditUser
     */
    public String getLastEditUser() {
        return lastEditUser;
    }

    /**
     * @param lastEditUser
     */
    public void setLastEditUser(String lastEditUser) {
        this.lastEditUser = lastEditUser;
    }
}