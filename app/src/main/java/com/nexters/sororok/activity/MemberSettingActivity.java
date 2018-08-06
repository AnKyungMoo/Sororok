package com.nexters.sororok.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.nexters.sororok.R;

/**
 *
 * 멤버세팅 액티비티
 * 멤버리스트 액티비티에서 설정을 누르면 이 액티비티로 이동한다
 */
public class MemberSettingActivity extends AppCompatActivity {

    private Button groupShareBtn, changeAdminBtn,groupManageBtn,groupRemoveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_setting);
        initComponent();
    }

    public void initComponent(){
        groupShareBtn = findViewById(R.id.btn_group_share);
        changeAdminBtn = findViewById(R.id.btn_admin_change);
        groupManageBtn = findViewById(R.id.btn_members_manage);
        groupRemoveBtn = findViewById(R.id.btn_group_remove);
    }
}
