package com.nexters.sororok.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GroupList {
    @SerializedName("extra_info")
    @Expose
    private String extra_info;

    @SerializedName("imageName")
    @Expose
    private String imageName;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("repositoryId")
    @Expose
    private int repositoryId;

    public String getExtra_info() {
        return extra_info;
    }

    public String getImageName() {
        return imageName;
    }

    public String getName() {
        return name;
    }

    public int getRepositoryId() {
        return repositoryId;
    }
}
