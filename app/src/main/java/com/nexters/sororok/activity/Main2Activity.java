package com.nexters.sororok.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.nexters.sororok.R;
import com.nexters.sororok.adapter.DessertAdapter;
import com.nexters.sororok.adapter.HistoryAdapter;

/*
* 툴바 애니메이션 도즈어어언!!!
* */
public class Main2Activity extends AppCompatActivity {

    private CollapsingToolbarLayout collapsingToolbar;
    private Toolbar toolbar;
    private AppBarLayout appBarLayout;
   // private boolean appBarExpanded = true;
    private LinearLayout linearLayout,linearBig;
    private EditText editText,editBig;
    private RelativeLayout relativeLayout;
    private RecyclerView recyclerView;
    private DessertAdapter dessertAdapter;
    private FloatingActionButton addGroupBtn;
    private ListView historyListView;
    private HistoryAdapter historyAdapter;
    private DrawerLayout drawerLayout;
    private Button historyBtn, settingBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2_main);
        initComponent();

        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(false); //default back button

        setAdapter();

        historyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(relativeLayout);
            }
        });

        addGroupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), NewGroupActivity.class);
                startActivityForResult(intent,500);
            }
        });

        settingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SettingActivity.class);
                startActivityForResult(intent,600);
            }
        });

       /* Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
                R.drawable.user_icon);

        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @SuppressWarnings("ResourceType")
            @Override
            public void onGenerated(Palette palette) {
                int vibrantColor = palette.getVibrantColor(R.color.colorPrimary);
                collapsingToolbar.setContentScrimColor(vibrantColor);
                collapsingToolbar.setStatusBarScrimColor(R.color.colorAccent);
            }
        });*/



        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                //  Vertical offset == 0 indicates appBar is fully expanded.
                if (Math.abs(verticalOffset)-appBarLayout.getTotalScrollRange() == 0) {
                    linearLayout.setVisibility(View.VISIBLE);
                    editText.setVisibility(View.VISIBLE);
                    linearBig.setVisibility(View.INVISIBLE);
                    editBig.setVisibility(View.INVISIBLE);
                    /*appBarExpanded = false;
                    invalidateOptionsMenu();*/
                } else {
                    linearLayout.setVisibility(View.GONE);
                    editText.setVisibility(View.GONE);
                    linearBig.setVisibility(View.VISIBLE);
                    editBig.setVisibility(View.VISIBLE);
                    /*appBarExpanded = true;
                    invalidateOptionsMenu();*/
                }
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
        dessertAdapter = new DessertAdapter(this);
        recyclerView.setAdapter(dessertAdapter);
    }

    public void initComponent(){
        toolbar = findViewById(R.id.anim_toolbar);
        setSupportActionBar(toolbar);
        appBarLayout = findViewById(R.id.appbar);
        collapsingToolbar = findViewById(R.id.collapsing_toolbar);
        linearLayout = findViewById(R.id.layout_small);
        editText = findViewById(R.id.edit_main_small);
        linearBig = findViewById(R.id.layout_big);
        editBig = findViewById(R.id.edit_big);
        drawerLayout = findViewById(R.id.layout_drawer);
        historyListView = findViewById(R.id.list_drawer);
        relativeLayout = findViewById(R.id.layout_linear);
        historyBtn = findViewById(R.id.btn_go_history);
        settingBtn = findViewById(R.id.btn_go_setting);
        recyclerView = findViewById(R.id.scrollableview);
        addGroupBtn = findViewById(R.id.btn_floating);
    }


}
