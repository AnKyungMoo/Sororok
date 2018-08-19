package com.nexters.sororok.model;

public class LoginUserInfo {
    private String phone;
    private String name;
    private String email;
    private String loginType;
    private String loginUid;

    public LoginUserInfo(String phone, String name, String email, String loginType, String loginUid) {
        this.phone = phone;
        this.name = name;
        this.email = email;
        this.loginType = loginType;
        this.loginUid = loginUid;
    }

    public String getPhone() {
        return phone;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getLoginType() {
        return loginType;
    }

    public String getLoginUid() {
        return loginUid;
    }
}
