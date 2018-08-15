package com.nexters.sororok.model;

import java.util.ArrayList;

public class Group {

    private int groupID;
    private String groupName;
    private String groupDetail;
    private String groupCode;
    private String groupMaster;
    private ArrayList<Integer> membersbyGroup;


    public int getGroupID() {
        return groupID;
    }

    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupDetail() {
        return groupDetail;
    }

    public void setGroupDetail(String groupDetail) {
        this.groupDetail = groupDetail;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public String getGroupMaster() {
        return groupMaster;
    }

    public void setGroupMaster(String groupMaster) {
        this.groupMaster = groupMaster;
    }


}
