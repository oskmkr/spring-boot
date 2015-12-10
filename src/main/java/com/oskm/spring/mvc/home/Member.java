package com.oskm.spring.mvc.home;

import com.oskm.support.BaseObject;

import java.util.Date;

public class Member extends BaseObject {

    private String userId;
    private String nickName;
    private Date addDate;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

}
