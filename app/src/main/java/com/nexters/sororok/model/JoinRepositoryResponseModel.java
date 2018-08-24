package com.nexters.sororok.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JoinRepositoryResponseModel {
    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("repositoryId")
    @Expose
    private int repositoryId;

    public String getMessage() {
        return message;
    }

    public int getRepositoryId() {
        return repositoryId;
    }
}
