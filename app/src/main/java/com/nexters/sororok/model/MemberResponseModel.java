package com.nexters.sororok.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MemberResponseModel {
    @SerializedName("authority")
    @Expose
    private int authority;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("phone")
    @Expose
    private String phone;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("imageName")
    @Expose
    private String imageName;

    @SerializedName("memberI")
    @Expose
    private int memberId;

    public int getAuthority() {
        return authority;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getName() {
        return name;
    }

    public String getImageName() {
        return imageName;
    }

    public int getMemberId() {
        return memberId;
    }
}
