package com.groceries.groceries.dtos;

public class JwtUser {

    private Integer user_id;
    private String mobile;

    public JwtUser() {

    }

    public JwtUser(Integer user_id, String mobile) {
        this.user_id = user_id;
        this.mobile = mobile;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}