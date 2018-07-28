package com.example.km.sororok.activity;

import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.km.sororok.R;
import com.example.km.sororok.adapter.MemberListAdapter;
import com.example.km.sororok.item.MemberListItem;

import java.util.ArrayList;

/**
 * 멤버리스트 액티비티
 * 그룹을 누르면 이 액티비티에서 멤버 명단이 뜸
 * 아 개어렵겠다
 */

public class MemberListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_list);

        ListView listView;
        MemberListAdapter adapter;



        adapter = new MemberListAdapter();



        listView=(ListView)findViewById(R.id.list_member);
        listView.setAdapter(adapter);

        adapter.addList(ContextCompat.getDrawable(this,R.drawable.blackbutton),"유니톤");
        adapter.addList(ContextCompat.getDrawable(this,R.drawable.blackbutton),"피곤해");
        adapter.addList(ContextCompat.getDrawable(this,R.drawable.blackbutton),"마지막");
        adapter.addList(ContextCompat.getDrawable(this,R.drawable.blackbutton),"유니톤");
        adapter.addList(ContextCompat.getDrawable(this,R.drawable.blackbutton),"피곤해");
        adapter.addList(ContextCompat.getDrawable(this,R.drawable.blackbutton),"마지막");
        adapter.addList(ContextCompat.getDrawable(this,R.drawable.blackbutton),"유니톤");
        adapter.addList(ContextCompat.getDrawable(this,R.drawable.blackbutton),"피곤해");
        adapter.addList(ContextCompat.getDrawable(this,R.drawable.blackbutton),"마지막");
        adapter.addList(ContextCompat.getDrawable(this,R.drawable.blackbutton),"유니톤");
        adapter.addList(ContextCompat.getDrawable(this,R.drawable.blackbutton),"피곤해");
        adapter.addList(ContextCompat.getDrawable(this,R.drawable.blackbutton),"마지막");


    }
}

