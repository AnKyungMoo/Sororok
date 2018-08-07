package com.nexters.sororok.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.nexters.sororok.R;
import com.nexters.sororok.adapter.HistoryAdapter;

/**
 * 메인 액티비티
 */
public class MainActivity extends AppCompatActivity{

    private Button profileButton,historyBtn,historyCloseBtn,settingBtn,addGroupBtn;
    private DrawerLayout drawerLayout;
    private ListView historyListView;
    private HistoryAdapter historyAdapter;
    private RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponent();
        setAdapter();



        historyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(relativeLayout);
            }
        });


        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newGroupIntent = new Intent(MainActivity.this, NewGroupActivity.class);
                startActivityForResult(newGroupIntent,100);
            }
        });

        settingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent settingIntent = new Intent(MainActivity.this, SettingActivity.class);
                startActivityForResult(settingIntent,200);

            }
        });

        addGroupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newGroupIntent = new Intent(MainActivity.this, NewGroupActivity.class);
                startActivityForResult(newGroupIntent, 300);
            }
        });


    }

    public void setAdapter(){
        historyAdapter = new HistoryAdapter(this);
        historyListView.setAdapter(historyAdapter);

        historyAdapter.addHistory("넥스터즈 13기 그룹의 곽희은 님의 번호가 변경되었습니다.");
        historyAdapter.addHistory("소로록이 새롭게 업데이트가 되었습니다.");
        historyAdapter.addHistory("넥터 13기 그룹이 새롭게 추가 되었습니다.");
    }

    public void initComponent(){

        if(getSupportActionBar() != null){
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }


        relativeLayout = findViewById(R.id.layout_linear);
        drawerLayout = findViewById(R.id.layout_drawer);
        historyListView = findViewById(R.id.list_drawer);
        historyBtn = findViewById(R.id.btn_go_history);
        settingBtn = findViewById(R.id.btn_go_setting);
        profileButton = findViewById(R.id.profileButton);
        historyCloseBtn = findViewById(R.id.btn_history_close);
        addGroupBtn = findViewById(R.id.btn_add_new_group);

    }

}
