package com.nexters.sororok.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GroupInfoModel {
    @SerializedName("repositoryId")
    @Expose
    private int repositoryId;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("extra_info")
    @Expose
    private String extra_info;

    @SerializedName("code")
    @Expose
    private String code;

    @SerializedName("imageName")
    @Expose
    private String imageName;

    public int getRepositoryId() {
        return repositoryId;
    }

    public String getName() {
        return name;
    }

    public String getExtra_info() {
        return extra_info;
    }

    public String getCode() {
        return code;
    }

    public String getImageName() {
        return imageName;
    }
}
