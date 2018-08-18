package com.nexters.sororok.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponseModel {
    @SerializedName("memberId")
    @Expose
    private String id;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("phone")
    @Expose
    private String phone;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("message")
    @Expose
    private String message;

    public String getId() {
        return id;
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

    public String getMessage() {
        return message;
    }
}
