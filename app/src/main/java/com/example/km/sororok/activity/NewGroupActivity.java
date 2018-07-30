package com.example.km.sororok.activity;

import android.content.Intent;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.km.sororok.R;


//그룹 생성 화면
public class NewGroupActivity extends AppCompatActivity {

    private static final int REQUEST_USER_GALLERY = 1;
    private ImageView imgGroupPhoto;
    private EditText groupName, groupExplain;
    private TextView seekValue;
    private Button completeBtn;
    private SeekBar totalMemberSeek;
    private String photoPath;


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

    public void initComponent(){
        imgGroupPhoto = (ImageView)findViewById(R.id.img_new_group);
        groupName = (EditText)findViewById(R.id.edit_group_name);
        groupExplain = (EditText)findViewById(R.id.edit_group_explain);
        completeBtn = (Button)findViewById(R.id.btn_new_group_complete);
        totalMemberSeek = (SeekBar)findViewById(R.id.seek_total_member);
        seekValue = (TextView)findViewById(R.id.txt_seek_value);
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
}
