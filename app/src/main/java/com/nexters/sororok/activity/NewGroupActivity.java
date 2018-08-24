package com.nexters.sororok.activity;

import android.content.Intent;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.nexters.sororok.R;
import com.nexters.sororok.asynctask.CreateGroupTask;
import com.nexters.sororok.model.GroupResponseModel;

import java.util.concurrent.ExecutionException;


//그룹 생성 화면
public class NewGroupActivity extends AppCompatActivity {

    private static final int REQUEST_USER_GALLERY = 1;
    private ImageView imgGroupPhoto;
    private EditText groupName, groupExplain;
    private TextView seekValue;
    private Button completeBtn,backBtn;
    private SeekBar totalMemberSeek;
    private String photoPath = null;
    private String groupCode = "a1b2c3";
    private int total_progress=2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_group);
        initComponent();
        imgGroupPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(NewGroupActivity.this, UserGalleryActivity.class);
                startActivityForResult(i, REQUEST_USER_GALLERY);
            }
        });
        seekListener();
    }

    public int getTotal_progress() {
        return total_progress;
    }

    public void setTotal_progress(int total_progress) {
        this.total_progress = total_progress;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK){
            switch (requestCode){
                case REQUEST_USER_GALLERY:
                    photoPath = data.getStringExtra("photo_path");
                    if(photoPath!=null)
                        Glide.with(this).load(photoPath).into(imgGroupPhoto);
                    break;
            }
        }
    }


    public void sendGroupInfo(){
        completeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isEmpty()){ //전부 채워져있으면
                    callRetrofit();
                    Intent intent = new Intent();
                    intent.putExtra("group_name", groupName.getText().toString());
                    intent.putExtra("group_explain", groupExplain.getText().toString());
                    intent.putExtra("total_member", getTotal_progress());
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });

    }

    public boolean isEmpty(){
        if(groupName.getText().toString().length() == 0 || groupExplain.getText().toString().length() == 0){
            Toast.makeText(getApplicationContext(), "모든 정보를 채워주세요", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    public void initComponent(){
        imgGroupPhoto = findViewById(R.id.img_new_group);
        groupName = findViewById(R.id.edit_group_name); //그룹 이름
        groupExplain = findViewById(R.id.edit_group_explain); //그룹 설명
        completeBtn = findViewById(R.id.btn_new_group_complete);
        sendGroupInfo();
        totalMemberSeek = findViewById(R.id.seek_total_member);
        seekValue = findViewById(R.id.txt_seek_value);
        backBtn = findViewById(R.id.btn_back);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        imgGroupPhoto.setBackground(new ShapeDrawable(new OvalShape()));
        if(Build.VERSION.SDK_INT >= 21) {
            imgGroupPhoto.setClipToOutline(true);
        }
    }

    public void seekListener(){
        totalMemberSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                int val = (progress * (seekBar.getWidth() - 2 * seekBar.getThumbOffset())) / seekBar.getMax();
                seekValue.setText(progress+"명");
                setTotal_progress(progress); //전체인원
                seekValue.setX(seekBar.getX() + val-18+ seekBar.getThumbOffset() / 2);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void callRetrofit() {
        CreateGroupTask createGroupTask = new CreateGroupTask();

        createGroupTask.execute(groupName.getText(),
                groupCode,
                Integer.valueOf(SplashActivity.localId),
                groupExplain.getText(),
                photoPath
        );

        try {
            GroupResponseModel groupResponseModel = createGroupTask.get();
            /* TODO: 뭔가 필요해지면 하자 */
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
