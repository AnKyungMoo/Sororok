package com.example.km.sororok.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.km.sororok.R;
import com.example.km.sororok.adapter.HistoryAdapter;

/**
 * 메인 액티비티
 */
public class MainActivity extends AppCompatActivity{

    private Button profileButton;
    private Button historyBtn;
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

    }

    public void setAdapter(){
        historyAdapter = new HistoryAdapter(this);
        historyListView.setAdapter(historyAdapter);
    }

    public void initComponent(){
        relativeLayout = (RelativeLayout) findViewById(R.id.layout_linear);
        drawerLayout = (DrawerLayout)findViewById(R.id.layout_drawer);
        historyListView = (ListView)findViewById(R.id.list_drawer);
        historyBtn = (Button)findViewById(R.id.btn_history);
        profileButton = (Button) findViewById(R.id.profileButton);
    }

}
