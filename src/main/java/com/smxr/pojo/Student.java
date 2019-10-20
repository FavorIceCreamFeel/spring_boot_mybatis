package com.smxr.pojo;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author LZY
 * @date 2019/8/23 11:21
 */
public class Student {
    private int userId;
    private String userName;
    private String email;
    private String pwd;
    private String phoneNum;
    private int status;

    public Student() {
    }

    public Student(int userId, String userName, String email, String pwd, String phoneNum, int status) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.pwd = pwd;
        this.phoneNum = phoneNum;
        this.status = status;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return this.userName;
    }

    @Override
    public int hashCode() {
        return this.userName.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this.toString().equals(obj.toString());
    }
}
