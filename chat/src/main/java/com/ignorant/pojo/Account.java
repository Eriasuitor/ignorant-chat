package com.ignorant.pojo;

import java.util.Date;
import javax.persistence.*;

public class Account {
    @Id
    private String userId;

    private String password;

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
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
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