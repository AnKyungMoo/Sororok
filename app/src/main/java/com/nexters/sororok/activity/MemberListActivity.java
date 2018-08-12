package com.nexters.sororok.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.nexters.sororok.R;
import com.nexters.sororok.adapter.MemberListwithAdapter;
import com.nexters.sororok.item.MemberListItem;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

/**
 * 멤버리스트 액티비티
 * 그룹을 누르면 이 액티비티에서 멤버 명단이 뜸
 * 아 개어렵겠다
 */

public class MemberListActivity extends AppCompatActivity {

    private MemberListwithAdapter listView;
    private Button groupManageBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_list);
        groupManageBtn = findViewById(R.id.btn_setting);
        Intent groupSettingIntent = new Intent(MemberListActivity.this, MemberSettingActivity.class);
//        startActivityForResult(groupSettingIntent, 400);

        listView=findViewById(R.id.list_member);



        ArrayList<String> name = new ArrayList<String>(Arrays.asList("강문정","한희영","곽희은","안경무","김혜리","주한빈","신상훈","Apple","2주남음","Nexters","살려주세요","index","Android","!!!!"));

        MemberListwithAdapter.MemberlistAdapter mAdapter= new MemberListwithAdapter.MemberlistAdapter(this);
        String consonant[] = listView.setKeywordList(name);

        listView.setAdapter(mAdapter);

        final TreeSet<Integer> checked = new TreeSet<>();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {


                if(adapterView.getAdapter().getItemViewType(position) == 0)
                {
                    MemberListItem item = (MemberListItem) adapterView.getAdapter().getItem(position);
                    if(checked.contains(item.getMemberID())){
                        checked.remove(item.getMemberID());
                        RelativeLayout rlList = view.findViewById(R.id.rlMemberList);
                        rlList.setBackgroundColor(Color.rgb(100,80,70));
                    } else {
                        checked.add(item.getMemberID());
                        RelativeLayout rlList = view.findViewById(R.id.rlMemberList);
                        rlList.setBackgroundColor(Color.rgb(255,255,255));
                    }

                }
            }
        });

        int i = 0;
        int j = 0;
        while(j<name.size()) {
            String firstString =name.get(j).substring(0,1);
            char firstChar = firstString.charAt(0);
            if(isKorean(firstChar))
                firstString=Direct(firstString);
            if (firstString.equals(consonant[i])) {
                mAdapter.addHeaderItem(new MemberListItem(null, consonant[i]));
                mAdapter.addItem(new MemberListItem(ContextCompat.getDrawable(this, R.drawable.blackbutton), name.get(j)));
                if(i<consonant.length-1)
                i++;
                j++;
            } else if(firstString.compareTo(consonant[i])<0){
                mAdapter.addItem(new MemberListItem(ContextCompat.getDrawable(this, R.drawable.blackbutton), name.get(j)));
                j++;
            } else {
                i++;
            }
        }




    }

    private boolean isKorean(char ch) {
        return ch >= Integer.parseInt("AC00", 16) && ch <= Integer.parseInt("D7A3", 16);
    }

    public String Direct(String name){
        char b =name.charAt(0);
        String chosung = null;
        int first = (b - 44032 ) / ( 21 * 28 );
        switch(first){
            case 0:
            case 1:
                chosung="ㄱ";
                break;
            case 2:
                chosung="ㄴ";
                break;
            case 3:
            case 4:
                chosung="ㄷ";
                break;
            case 5:
                chosung="ㄹ";
                break;
            case 6:
                chosung="ㅁ";
                break;
            case 7:
            case 8:
                chosung="ㅂ";
                break;
            case 9:
            case 10:
                chosung="ㅅ";
                break;
            case 11:
                chosung="ㅇ";
                break;
            case 12:
            case 13:
                chosung="ㅈ";
                break;
            case 14:
                chosung="ㅊ";
                break;
            case 15:
                chosung="ㅋ";
                break;
            case 16:
                chosung="ㅌ";
                break;
            case 17:
                chosung="ㅍ";
                break;
            case 18:
                chosung="ㅎ";
                break;

        }

        return chosung;
    }
}

