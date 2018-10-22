package com.ignorant.pojo;

import java.util.Date;
import javax.persistence.*;

@Table(name = "msg_record")
public class MsgRecord {
    @Id
    private Long id;

    private String userId;

    private String from;

    private String type;

    private String content;

    private Date createDate;

    private Date lastEditData;

    private String createUser;

    private String lastEditUser;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

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
     * @return from
     */
    public String getFrom() {
        return from;
    }

    /**
     * @param from
     */
    public void setFrom(String from) {
        this.from = from;
    }

    /**
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content
     */
    public void setContent(String content) {
        this.content = content;
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