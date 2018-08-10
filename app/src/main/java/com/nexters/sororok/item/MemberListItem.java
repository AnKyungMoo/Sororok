package com.nexters.sororok.item;


import android.graphics.drawable.Drawable;

public class MemberListItem{
    private Drawable memberProfile;
    private String memberName;



    private String memberNumber;

    public MemberListItem(Drawable memberProfile, String memberName){
        this.memberName = memberName;
        this.memberProfile = memberProfile;
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