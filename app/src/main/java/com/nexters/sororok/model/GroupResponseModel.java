package com.nexters.sororok.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GroupResponseModel {
    @SerializedName("code")
    @Expose
    private String code;

    @SerializedName("memberId")
    @Expose
    private int memberId;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("repositoryId")
    @Expose
    private int repositoryId;

    public String getCode() {
        return code;
    }

    public int getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    public int getRepositoryId() {
        return repositoryId;
    }
}
