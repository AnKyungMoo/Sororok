package com.nexters.sororok.model;

public class Member {
    private int memberId;
    private String name;
    private String phone;
    private String email;
    private int authority;
    private String imageName;

    public Member(int memberId, String name, String phone, String email, int authority,String imageName) {
        this.memberId = memberId;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.authority = authority;
        this.imageName = imageName;

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

    public String getImageName() {
        return imageName;
    }
}
