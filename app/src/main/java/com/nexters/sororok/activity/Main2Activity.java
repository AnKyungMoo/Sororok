package com.nexters.sororok.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
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
    private AppBarLayout appBarLayout;
    private RecyclerView recyclerView;
    private DessertAdapter dessertAdapter;
    private boolean appBarExpanded = true;
    private Menu collapsedMenu;
    private LinearLayout linearLayout,linearBig;
    private EditText editText,editBig;
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private ListView historyListView;
    private HistoryAdapter historyAdapter;
    private Button historyBtn, settingBtn;
    private RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity2_main);

        toolbar = (Toolbar) findViewById(R.id.anim_toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(false); //default back button

        appBarLayout = (AppBarLayout) findViewById(R.id.appbar);

        collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
       // collapsingToolbar.setTitle("툴바테스트");
        linearLayout = findViewById(R.id.layout_small);
        editText = findViewById(R.id.edit_main_small);
        linearBig = findViewById(R.id.layout_big);
        editBig = findViewById(R.id.edit_big);

        drawerLayout = findViewById(R.id.layout_drawer);
        historyListView = findViewById(R.id.list_drawer);
        relativeLayout = findViewById(R.id.layout_linear);
        historyBtn = findViewById(R.id.btn_go_history);
        settingBtn = findViewById(R.id.btn_go_setting);
        setAdapter();

        historyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(relativeLayout);
            }
        });

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
                R.drawable.user_icon);

        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @SuppressWarnings("ResourceType")
            @Override
            public void onGenerated(Palette palette) {
                int vibrantColor = palette.getVibrantColor(R.color.colorPrimary);
                collapsingToolbar.setContentScrimColor(vibrantColor);
                collapsingToolbar.setStatusBarScrimColor(R.color.colorAccent);
            }
        });

        recyclerView = findViewById(R.id.scrollableview);

        //  Use when your list size is constant for better performance

        recyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        dessertAdapter = new DessertAdapter(this);
        recyclerView.setAdapter(dessertAdapter);

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                //Log.d(AnimateToolbar.class.getSimpleName(), "onOffsetChanged: verticalOffset: " + verticalOffset);

                //  Vertical offset == 0 indicates appBar is fully expanded.
                if (Math.abs(verticalOffset)-appBarLayout.getTotalScrollRange() == 0) {
                   // Toast.makeText(getApplicationContext(),"111",Toast.LENGTH_SHORT).show();
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
                   // Toast.makeText(getApplicationContext(),"222",Toast.LENGTH_SHORT).show();
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
    }

/*
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (collapsedMenu != null
                && (!appBarExpanded || collapsedMenu.size() != 1)) {
            //collapsed
           *//* collapsedMenu.add("Add")
                    .setIcon(R.drawable.blackbutton)
                    .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);*//*
            Toast.makeText(getApplicationContext(),"11",Toast.LENGTH_LONG).show();
        } else {
            //expanded
            Toast.makeText(getApplicationContext(),"22",Toast.LENGTH_LONG).show();
        }
        return super.onPrepareOptionsMenu(collapsedMenu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        //collapsedMenu = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.action_settings:
                return true;
        }
        if (item.getTitle() == "Add") {
            //Toast.makeText(this, "clicked add", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }*/


}
