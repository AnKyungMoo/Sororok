package com.nexters.sororok.model;

public class LoginRequestModel {
    private final String type;
    private final String uid;

    public LoginRequestModel(String type, String uid) {
        this.type = type;
        this.uid = uid;
    }
}
