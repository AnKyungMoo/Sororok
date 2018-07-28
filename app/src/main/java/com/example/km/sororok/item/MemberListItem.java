package com.example.km.sororok.item;


import android.graphics.drawable.Drawable;

public class MemberListItem{
    private Drawable memberProfile;
    private String memberName;

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
}