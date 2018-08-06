package com.nexters.sororok.activity;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.nexters.sororok.R;
import com.nexters.sororok.adapter.MemberListAdapter;
import com.nexters.sororok.item.MemberListItem;

import java.util.ArrayList;

/**
 * 멤버리스트 액티비티
 * 그룹을 누르면 이 액티비티에서 멤버 명단이 뜸
 * 아 개어렵겠다
 */

public class MemberListActivity extends AppCompatActivity {

    ListView listView;
    MemberListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_list);


        listView=(ListView)findViewById(R.id.list_member);



        adapter = new MemberListAdapter(this);

        String[] consonant = {"ㄱ","ㄴ","ㄷ","ㄹ","ㅁ","ㅂ","ㅅ","ㅇ","ㅈ","ㅊ","ㅋ","ㅌ","ㅍ","ㅎ"};
        ArrayList<String> name = new ArrayList<>();
        name.add("강문정");
        name.add("곽희은");
        name.add("김혜리");
        name.add("남수민");
        name.add("신상훈");
        name.add("안경무");
        name.add("주한빈");
        name.add("한희영");
        int i = 0;
        int j = 0;
        while(j<name.size()) {
            if (Direct(name.get(j)) == consonant[i]) {
                adapter.addHeaderItem(new MemberListItem(null, consonant[i]));
                adapter.addItem(new MemberListItem(ContextCompat.getDrawable(this, R.drawable.blackbutton), name.get(j)));
                i++;
                j++;
            } else if(Direct(name.get(j)).compareTo(consonant[i])<0){
                adapter.addItem(new MemberListItem(ContextCompat.getDrawable(this, R.drawable.blackbutton), name.get(j)));
                j++;
            } else {
                i++;
            }
        }

        listView.setAdapter(adapter);


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

