package com.nexters.sororok.model;

import java.util.ArrayList;

public class Member {
    private int memberId;
    private String name;
    private String phone;
    private String email;
    private int authority;

    public Member(int memberId, String name, String phone, String email, int authority) {
        this.memberId = memberId;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.authority = authority;
    }

    public int getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public int getAuthority() {
        return authority;
    }

    public void setAuthority(int authority) {
        this.authority = authority;
    }
}
