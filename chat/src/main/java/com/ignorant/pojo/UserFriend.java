package com.ignorant.pojo;

import java.util.Date;
import javax.persistence.*;

@Table(name = "user_friend")
public class UserFriend {
    @Id
    private String userId;

    private String friendId;

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
     * @return friendId
     */
    public String getFriendId() {
        return friendId;
    }

    /**
     * @param friendId
     */
    public void setFriendId(String friendId) {
        this.friendId = friendId;
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