package com.chenke.flashcards.bean;

public class User {
    private String userName;  //用户名
    private String phone;  //电话号码

    public User() {

    }

    public User(String userName, String phone) {
        this.userName = userName;
        this.phone = phone;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
