package com.ignorant.chat.pojo;

import java.util.Date;
import javax.persistence.*;

@Table(name = "msg_flag")
public class MsgFlag {
    @Id
    private String userId;

    private Long peak;

    private Long current;

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
     * @return peak
     */
    public Long getPeak() {
        return peak;
    }

    /**
     * @param peak
     */
    public void setPeak(Long peak) {
        this.peak = peak;
    }

    /**
     * @return current
     */
    public Long getCurrent() {
        return current;
    }

    /**
     * @param current
     */
    public void setCurrent(Long current) {
        this.current = current;
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