package com.nexters.sororok.item;

import android.widget.RelativeLayout;

public class GroupListItem {
    public String signInText; // 가입됨
    public int groupImage;      // 그룹 큰 사진
    public String groupName;    // 그룹 이름
    //public int groupSmallImage; // 그룹 작은 사진
    public String groupContent; // 그룹 내용
    public RelativeLayout relativeLayout;
    //private int goButtonImage;        // 그룹에 들어가는 버튼

    public GroupListItem(String signInText, int groupImage, String groupName, String groupContent) {
        this.signInText = signInText;
        this.groupImage = groupImage;
        this.groupName = groupName;
       // this.groupSmallImage = groupSmallImage;
        this.groupContent = groupContent;
        //this.goButtonImage = goButtonImage;
    }
}
