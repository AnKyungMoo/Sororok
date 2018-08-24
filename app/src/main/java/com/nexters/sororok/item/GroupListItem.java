package com.nexters.sororok.item;

public class GroupListItem {
    public String signInText; // 가입됨
    public String groupImage;      // 그룹 큰 사진
    public String groupName;    // 그룹 이름
    public String groupContent; // 그룹 내용
    public int groupId;

    public GroupListItem(String signInText, String groupImage, String groupName, String groupContent, int groupId) {
        this.signInText = signInText;
        this.groupImage = groupImage;
        this.groupName = groupName;
        this.groupContent = groupContent;
        this.groupId = groupId;
    }
}
