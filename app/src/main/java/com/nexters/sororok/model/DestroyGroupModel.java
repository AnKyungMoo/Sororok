package com.nexters.sororok.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DestroyGroupModel {
    @SerializedName("code")
    @Expose
    private int code;

    @SerializedName("message")
    @Expose
    private String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
