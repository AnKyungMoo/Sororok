package com.nexters.sororok.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RefreshCodeModel {
    @SerializedName("code")
    @Expose
    private int code;

    @SerializedName("groupCode")
    @Expose
    private String groupCode;

    @SerializedName("message")
    @Expose
    private String message;

    public int getCode() {
        return code;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public String getMessage() {
        return message;
    }
}
