package com.mehfatitem.model;

public class User {

    public int userId;
    public String contact;
    public String userName;
    public String password;
    public String email;
    public long saveDate;

    public User(int userId , String contact, String userName, String password, String email, long saveDate) {
    	this.userId = userId;
        this.contact = contact;
        this.password = password;
        this.userName = userName;
        this.email = email;
        this.saveDate = saveDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getSaveDate() {
        return saveDate;
    }

    public void setSaveDate(long saveDate) {
        this.saveDate = saveDate;
    }
}
