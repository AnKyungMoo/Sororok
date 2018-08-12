package com.nexters.sororok.item;


import android.graphics.drawable.Drawable;

public class MemberListItem{
    private Drawable memberProfile;
    private String memberName;
    private String memberNumber;
    private int memberID;
    private boolean checked = false;

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }


    public int getMemberID() {
        return memberID;
    }

    public void setMemberID(int memberID) {
        this.memberID = memberID;
    }



    public MemberListItem(Drawable memberProfile, String memberName, int memberID){
        this.memberName = memberName;
        this.memberProfile = memberProfile;
        this.memberID = memberID;
    }

    public Drawable getMemberProfile() {
        return memberProfile;
    }

    public void setMemberProfile(Drawable memberProfile) {
        this.memberProfile = memberProfile;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberNumber() {
        return memberNumber;
    }

    public void setMemberNumber(String memberNumber) {
        this.memberNumber = memberNumber;
    }


}