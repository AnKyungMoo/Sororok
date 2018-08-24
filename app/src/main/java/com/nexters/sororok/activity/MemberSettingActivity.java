package com.nexters.sororok.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nexters.sororok.R;
import com.nexters.sororok.asynctask.DestroyGroupTask;
import com.nexters.sororok.asynctask.GroupCodeTask;
import com.nexters.sororok.asynctask.GroupInfoTask;
import com.nexters.sororok.asynctask.RefreshCodeTask;
import com.nexters.sororok.model.DestroyGroupModel;
import com.nexters.sororok.model.DestroyRequestModel;
import com.nexters.sororok.model.GroupInfoModel;
import com.nexters.sororok.model.RefreshCodeModel;
import com.nexters.sororok.model.UpdateCodeModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

/**
 *
 * 멤버세팅 액티비티
 * 멤버리스트 액티비티에서 설정을 누르면 이 액티비티로 이동한다
 */
public class MemberSettingActivity extends AppCompatActivity {

    private Button groupShareBtn, changeAdminBtn,groupManageBtn,groupRemoveBtn,groupShareBtn2;
    private Button backBtn,exitGroupBtn,optionBtn1,optionBtn2,optionBtn3, refreshButton;
    private RelativeLayout animLayout,mainLayout;
    private Animation slideUpAnimation, slideDownAnimation;
    private LinearLayout defalutLayout, nextLayout,share,manage,change,boom;
    private TextView mainTitle, subTitle, groupCode;
    private int groupid;
    /* TODO: 앞에서부터 데이터 가져오자 */
    private int repositoryId = 13;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_setting);
        initComponent();
        getGroupInfo();
        Intent intentForGet = getIntent();
        groupid=intentForGet.getIntExtra("bgroupid",-1);
        boom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainTitle.setText("정말 그룹을 폭파하시려구요?");
                subTitle.setText("그룹에 저장된 모든 번호가 삭제됩니다.\n그룹장을 다른사람에게 넘기는 것을 추천합니다.");
                optionBtn3.setVisibility(View.INVISIBLE);
                optionBtn1.setVisibility(View.VISIBLE);
                optionBtn2.setVisibility(View.VISIBLE);
                animLayout.startAnimation(slideUpAnimation);
                mainLayout.setBackgroundColor(Color.argb(80,50,50,50));
                animLayout.setVisibility(View.VISIBLE);
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if(nextLayout.getVisibility() == View.VISIBLE){
                   nextLayout.setVisibility(View.INVISIBLE);
                   defalutLayout.setVisibility(View.VISIBLE);
               }else{
                   finish();
               }
            }
        });

        mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(animLayout.getVisibility()==View.VISIBLE){
                    animLayout.startAnimation(slideDownAnimation);
                    mainLayout.setBackgroundColor(Color.rgb(255,255,255));
                    animLayout.setVisibility(View.INVISIBLE);
                }
            }
        });

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MemberSettingActivity.this, ChangeAdminActivity.class);
                startActivityForResult(intent,100);
            }
        });

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                defalutLayout.setVisibility(View.INVISIBLE);
                nextLayout.setVisibility(View.VISIBLE);

            }
        });

        optionBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DestroyGroupTask destroyGroupTask = new DestroyGroupTask();

                destroyGroupTask.execute(new DestroyRequestModel(Integer.valueOf(SplashActivity.localId), repositoryId));

                try {
                    DestroyGroupModel destroyGroupModel = destroyGroupTask.get();

                    Intent intent = new Intent(MemberSettingActivity.this, TestActivity.class);
                    startActivity(intent);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        });

        groupShareBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*try {

                    //https://developers.kakao.com/docs/android/kakaotalk-link#%EB%A9%94%EC%8B%9C%EC%A7%80-%ED%83%80%EC%9E%85-%EC%86%8C%EA%B0%9C
                    KakaoLink kakaoLink = KakaoLink.getKakaoLink(getApplicationContext());
                    KakaoTalkLinkMessageBuilder messageBuilder = kakaoLink.createKakaoTalkLinkMessageBuilder();
                    messageBuilder.addText(groupCode.getText().toString());
                    kakaoLink.sendMessage(messageBuilder,getApplicationContext());
                } catch (KakaoParameterException e) {
                    e.printStackTrace();
                }*/
                Intent sendIntent = new Intent(Intent.ACTION_SEND);
                sendIntent.setType("text/plain");
                //sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_SUBJECT,"넥스터즈 13기");
                sendIntent.putExtra(Intent.EXTRA_TEXT, groupCode.getText());
                Intent chooser = Intent.createChooser(sendIntent, "공유하기");
                startActivity(chooser);
            }
        });

        exitGroupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainTitle.setText("정말 그룹을 나가시려구요?");
                subTitle.setText("그룹 탈퇴에 대한 알림\n그룹원에게 알림이 가지 않습니다.");
                optionBtn1.setText("인수인계");
                optionBtn2.setText("그룹삭제");
                optionBtn3.setVisibility(View.VISIBLE);
                optionBtn1.setVisibility(View.INVISIBLE);
                optionBtn2.setVisibility(View.INVISIBLE);
                animLayout.startAnimation(slideUpAnimation);
                mainLayout.setBackgroundColor(Color.argb(80,50,50,50));
                animLayout.setVisibility(View.VISIBLE);
            }
        });

        manage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent manageIntent = new Intent(getApplicationContext(),MemberBanActivity.class);
                manageIntent.putExtra("agroupid",groupid);
                startActivity(manageIntent);
            }
        });

        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                refreshGroupCode();
            }
        });
    }

    public void initComponent(){
        share=findViewById(R.id.llGroupShare);
        manage=findViewById(R.id.llGroupManage);
        change=findViewById(R.id.llGroupChange);
        boom=findViewById(R.id.llGroupBoom);
        groupShareBtn = findViewById(R.id.btn_group_share);
        groupShareBtn2 = findViewById(R.id.btn_group_share2);
        changeAdminBtn = findViewById(R.id.btn_admin_change);
        groupManageBtn = findViewById(R.id.btn_members_manage);
        groupRemoveBtn = findViewById(R.id.btn_group_remove);
        animLayout =  findViewById(R.id.layout_animation);
        mainLayout = findViewById(R.id.layout_main);
        backBtn = findViewById(R.id.btn_back);
        exitGroupBtn = findViewById(R.id.btn_exit_group);
        defalutLayout = findViewById(R.id.layout_default_screen);
        nextLayout = findViewById(R.id.layout_click_share_group);
        optionBtn1 = findViewById(R.id.btn_menu_1);
        optionBtn2 = findViewById(R.id.btn_menu_2);
        optionBtn3 = findViewById(R.id.btn_menu_3);
        mainTitle = findViewById(R.id.txt_menu_main_title);
        subTitle = findViewById(R.id.txt_menu_sub_title);
        groupCode = findViewById(R.id.txt_group_code);
        slideUpAnimation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_up_animation);
        slideDownAnimation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_down_animation);
        refreshButton = findViewById(R.id.button_code_refresh);
    }

    private void getGroupInfo() {
        GroupInfoTask groupInfoTask = new GroupInfoTask();

        groupInfoTask.execute(repositoryId);

        try {
            GroupInfoModel groupInfoModel = groupInfoTask.get();

            groupCode.setText(groupInfoModel.getCode());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    private void refreshGroupCode() {
        RefreshCodeTask refreshCodeTask = new RefreshCodeTask();

        refreshCodeTask.execute(new UpdateCodeModel(repositoryId));

        try {
            RefreshCodeModel refreshCodeModel = refreshCodeTask.get();

            groupCode.setText(refreshCodeModel.getGroupCode());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
