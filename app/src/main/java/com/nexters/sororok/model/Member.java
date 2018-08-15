package com.nexters.sororok.model;

import java.util.ArrayList;

public class Member {
    private int memberID;
    private String memberName;
    private String memberPhone;
    private String memberPhoto;
    private ArrayList<Integer> groupsByMember;

    public int getMemberID() {
        return memberID;
    }

    public void setMemberID(int memberID) {
        this.memberID = memberID;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberPhone() {
        return memberPhone;
    }

    public void setMemberPhone(String memberPhone) {
        this.memberPhone = memberPhone;
    }

    public String getMemberPhoto() {
        return memberPhoto;
    }

    public void setMemberPhoto(String memberPhoto) {
        this.memberPhoto = memberPhoto;
    }

    public ArrayList<Integer> getGroupsByMember(){
        return groupsByMember;
    }

    public void addGroup(int group){
        this.groupsByMember.add(group);
    }

    public void removeGroup(int group){
        this.groupsByMember.remove(group);
    }

   public ArrayList<Integer> getGroupsbyMember(){
        return groupsByMember;
   }

}
