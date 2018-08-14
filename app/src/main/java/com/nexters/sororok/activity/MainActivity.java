package com.nexters.sororok.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.nexters.sororok.R;
import com.nexters.sororok.adapter.DessertAdapter;
import com.nexters.sororok.adapter.GroupAdapter;
import com.nexters.sororok.adapter.HistoryAdapter;
import com.nexters.sororok.item.GroupListItem;

import java.util.ArrayList;

/**
 * 메인 액티비티
 */
public class MainActivity extends AppCompatActivity{

    private Button profileButton,historyBtn,historyCloseBtn,settingBtn,addGroupBtn, goGroupBtn;
    private DrawerLayout drawerLayout;
    private ListView historyListView;
    private HistoryAdapter historyAdapter;
    private RelativeLayout relativeLayout;
    private CustomDialog customDialog;
    private RecyclerView recyclerView;
    private GroupAdapter groupAdapter;

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
                Intent newGroupIntent = new Intent(MainActivity.this, MyPageActivity.class);
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

        goGroupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customDialog = new CustomDialog(MainActivity.this);
                customDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAni;
                customDialog.show();
                customDialog.setFlag(1);
                customDialog.getEditText().setVisibility(View.VISIBLE);
                customDialog.setTitle("그룹코드를 입력해주세요.");

            }
        });


    }

    public void setAdapter(){
        historyAdapter = new HistoryAdapter(this);
        historyListView.setAdapter(historyAdapter);

        historyAdapter.addHistory("넥스터즈 13기 그룹의 곽희은 님의 번호가 변경되었습니다.");
        historyAdapter.addHistory("소로록이 새롭게 업데이트가 되었습니다.");
        historyAdapter.addHistory("넥터 13기 그룹이 새롭게 추가 되었습니다.");

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        /*TODO: 서버에서 데이터를 받아올 때 까지 사용할 더미 데이터*/
        ArrayList<GroupListItem> groupListItems = new ArrayList<GroupListItem>();
        groupListItems.add(new GroupListItem("가입됨", R.drawable.xbutton,
                "넥스터즈 13기", R.drawable.xbutton, "1번방"));
        groupListItems.add(new GroupListItem("가입됨", R.drawable.xbutton,
                "(주)소로록", R.drawable.xbutton, "7번방"));
        groupListItems.add(new GroupListItem("가입안됨", R.drawable.xbutton,
                "빈방", R.drawable.xbutton, "0번방"));

        groupAdapter = new GroupAdapter(groupListItems);
        recyclerView.setAdapter(groupAdapter);
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

        goGroupBtn = findViewById(R.id.btn_go_group);

        recyclerView = findViewById(R.id.recycler_group);
    }

}
