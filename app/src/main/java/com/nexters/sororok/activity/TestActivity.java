package com.nexters.sororok.activity;

import android.content.Intent;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nexters.sororok.R;
import com.nexters.sororok.adapter.GroupAdapter;
import com.nexters.sororok.adapter.HistoryAdapter;
import com.nexters.sororok.asynctask.GroupListTask;
import com.nexters.sororok.item.GroupListItem;
import com.nexters.sororok.model.GroupList;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class TestActivity extends AppCompatActivity
      implements AppBarLayout.OnOffsetChangedListener {

    private boolean mIsTheTitleVisible = false;
    private boolean mIsThePersonDetailsContainerVisible = true;

    private static final int ALPHA_ANIMATIONS_DURATION = 100;
    private static final float PERCENTAGE_TO_HIDE_PERSON_DETAILS = 0.3f;
    private static final float PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR = 0.9f;

    private TextView tvTitleToolBar;
    private AppBarLayout appBarLayout;
    private Button settingBtn1, settingBtn2, historyBtn1, historyBtn2, settingBtn, historyBtn;
    private TextView userName;
    private RecyclerView recyclerView;
    private GroupAdapter groupAdapter;
    private ListView historyListView;
    private HistoryAdapter historyAdapter;
    private DrawerLayout drawerLayout;
    private RelativeLayout relativeLayout;
    private FloatingActionButton floatingActionButton;
    private ImageView userImage;
    private ArrayList<GroupListItem> groupListItems;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main4);
        findViewById();
        setAdapter();
        buttonConnect();
       // appBarLayout.addOnOffsetChangedListener(this);
       // startAlphaAnimation(tvTitleToolBar, 0, View.INVISIBLE);
    }

    @Override
    protected void onResume() {
        super.onResume();

        getGroupList();
    }

    private void findViewById() {
        /*tvTitleToolBar = findViewById(R.id.tvTitleToolBar);
        appBarLayout = findViewById(R.id.appBarLayout);*/
        userName = findViewById(R.id.txt_user_name);
      /*  settingBtn2 = findViewById(R.id.btn_setting_2);
        settingBtn1 = findViewById(R.id.btn_setting_1);
        historyBtn1 = findViewById(R.id.btn_history_1);
        historyBtn2 = findViewById(R.id.btn_history_2);*/
        historyBtn = findViewById(R.id.btn_history);
        settingBtn = findViewById(R.id.btn_setting);
        recyclerView = findViewById(R.id.recyclerview);
        historyListView = findViewById(R.id.list_drawer);
        drawerLayout = findViewById(R.id.layout_drawer);
        relativeLayout = findViewById(R.id.layout_linear);
        floatingActionButton = findViewById(R.id.btn_floating);
        userImage = findViewById(R.id.img_user);
        userImage.setBackground(new ShapeDrawable(new OvalShape()));
        if(Build.VERSION.SDK_INT >= 21) {
            userImage.setClipToOutline(true);
        }
    }

    public void buttonConnect(){
        historyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(relativeLayout);
            }
        });
        /*historyBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(relativeLayout);
            }
        });*/
        settingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SettingActivity.class);
                startActivityForResult(intent,100);
            }
        });
       /* settingBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SettingActivity.class);
                startActivityForResult(intent,100);
            }
        });*/
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), NewGroupActivity.class);
                startActivityForResult(intent, 200);
            }
        });
        userImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MyPageActivity.class);
                startActivityForResult(intent,300);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK){
            switch (requestCode){
                //그룹생성
                case 200:
                    String groupName = data.getExtras().getString("group_name");
                    String groupExplain = data.getExtras().getString("group_explain");
                    int totalMember = data.getExtras().getInt("total_member");
                    //addGroup(groupName, groupExplain);
                    break;
                case 300:
                    String photoPath = data.getExtras().getString("photo_path");
                    Glide.with(this).load(photoPath).into(userImage);
                    break;
            }
        }
    }

    /*
    public void addGroup(String groupName, String groupExplain){
        groupListItems.add(0, new GroupListItem("가입됨", R.drawable.combined_shape_rectangle_2_path_rectangle_copy_oval_copy_3_oval_copy_5_mask_copy_copy_copy_copy,
                groupName, groupExplain));
        /*groupListItems.set(0, new GroupListItem("가입됨", R.drawable.xbutton,
                groupName, R.drawable.xbutton, groupExplain));
        groupAdapter.notifyDataSetChanged();
    }*/

    public void setAdapter(){
        historyAdapter = new HistoryAdapter(this);
        historyListView.setAdapter(historyAdapter);
        historyAdapter.addHistory("넥스터즈 13기 그룹의 곽희은 님의 번호가 변경되었습니다.");
        historyAdapter.addHistory("소로록이 새롭게 업데이트가 되었습니다.");
        historyAdapter.addHistory("넥터 13기 그룹이 새롭게 추가 되었습니다.");

       /* recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        *//*TODO: 서버에서 데이터를 받아올 때 까지 사용할 더미 데이터*//*
        groupListItems = new ArrayList<>();
        groupListItems.add(new GroupListItem("가입됨", R.drawable.xbutton,
                "넥스터즈 13기", R.drawable.xbutton, "1번방"));
        groupListItems.add(new GroupListItem("가입됨", R.drawable.xbutton,
                "(주)소로록", R.drawable.xbutton, "7번방"));
        groupListItems.add(new GroupListItem("가입안됨", R.drawable.xbutton,
                "빈방", R.drawable.xbutton, "0번방"));

        groupAdapter = new GroupAdapter(groupListItems);
        recyclerView.setAdapter(groupAdapter);*/
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

        int maxScroll = appBarLayout.getTotalScrollRange();
        float percentage = (float) Math.abs(verticalOffset) / (float) maxScroll;

        handleAlphaOnPersonDetails(percentage);
        handleToolBarTitleVisibility(percentage);
    }

    public static void startAlphaAnimation(View v, long duration, int visibility) {
        AlphaAnimation alphaAnimation = (visibility == View.VISIBLE)
                ? new AlphaAnimation(0f, 1f)
                : new AlphaAnimation(1f, 0f);

        alphaAnimation.setDuration(duration);
        alphaAnimation.setFillAfter(true);
        v.startAnimation(alphaAnimation);
    }

    private void handleAlphaOnPersonDetails(float percentage) {

        if (percentage >= PERCENTAGE_TO_HIDE_PERSON_DETAILS) {

            settingBtn1.setVisibility(View.INVISIBLE);
            historyBtn1.setVisibility(View.INVISIBLE);

            if (mIsThePersonDetailsContainerVisible) {
                startAlphaAnimation(userName, ALPHA_ANIMATIONS_DURATION, View.INVISIBLE);
                settingBtn2.setVisibility(View.VISIBLE);
                historyBtn2.setVisibility(View.VISIBLE);
                mIsThePersonDetailsContainerVisible = false;
            }
        } else {
            if (!mIsThePersonDetailsContainerVisible) {
                settingBtn1.setVisibility(View.VISIBLE);
                historyBtn1.setVisibility(View.VISIBLE);
                startAlphaAnimation(userName, ALPHA_ANIMATIONS_DURATION, View.VISIBLE);
                settingBtn2.setVisibility(View.INVISIBLE);
                historyBtn2.setVisibility(View.INVISIBLE);
                mIsThePersonDetailsContainerVisible = true;
            }
        }
    }

    private void handleToolBarTitleVisibility(float percentage) {

        if (percentage >= PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR) {
            if (!mIsTheTitleVisible) {
                startAlphaAnimation(tvTitleToolBar, ALPHA_ANIMATIONS_DURATION, View.VISIBLE);
                mIsTheTitleVisible = true;
            }
        } else {
            if (mIsTheTitleVisible) {
                startAlphaAnimation(tvTitleToolBar, ALPHA_ANIMATIONS_DURATION, View.INVISIBLE);
                mIsTheTitleVisible = false;
            }
        }
    }

    private void getGroupList() {
        GroupListTask groupListTask = new GroupListTask();

        groupListTask.execute(Integer.valueOf(SplashActivity.localId));

        try {
            List<GroupList> groupList = groupListTask.get();

            recyclerView.setHasFixedSize(true);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(linearLayoutManager);

            groupListItems = new ArrayList<>();

            for (int i = 0; i < groupList.size(); ++i) {
                groupListItems.add(new GroupListItem("가입됨",
                        groupList.get(i).getImageName(),
                        groupList.get(i).getName(),
                        groupList.get(i).getExtra_info()
                ));
            }

            groupAdapter = new GroupAdapter(groupListItems);
            recyclerView.setAdapter(groupAdapter);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
