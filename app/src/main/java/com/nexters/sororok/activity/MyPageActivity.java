package com.nexters.sororok.activity;

import android.content.Intent;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nexters.sororok.R;

public class MyPageActivity extends AppCompatActivity {

    private static final int REQUEST_USER_GALLERY = 1;

    private Button backBtn,logoutBtn;
    private ImageView imgUserPhoto;
    private TextView saveText;
    private String photoPath;
    private CustomDialog customDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page);
        initComponent();
        imgUserPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyPageActivity.this, UserGalleryActivity.class);
                startActivityForResult(intent, REQUEST_USER_GALLERY);
            }
        });

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customDialog = new CustomDialog(MyPageActivity.this);
                customDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAni;
                customDialog.show();
                customDialog.setTitle("로그아웃 하시겠습니까?");
            }
        });

        saveText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("photo_path", photoPath);
               // Toast.makeText(getApplicationContext(), photoPath+"1111", Toast.LENGTH_SHORT).show();
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    public void initComponent(){
        backBtn = findViewById(R.id.btn_back);
        logoutBtn = findViewById(R.id.btn_logout);
        saveText = findViewById(R.id.txt_save);
       // photoPath = "drawable://"+R.drawable.user_icon;
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        imgUserPhoto = findViewById(R.id.img_user_photo);
       /* imgUserPhoto.setBackground(new ShapeDrawable(new OvalShape()));
        if(Build.VERSION.SDK_INT >= 21) {
            imgUserPhoto.setClipToOutline(true);
        }*/

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_USER_GALLERY && resultCode == RESULT_OK) {
            photoPath = data.getStringExtra("photo_path"); //사진경로
            imgUserPhoto.setBackground(new ShapeDrawable(new OvalShape()));
            if(Build.VERSION.SDK_INT >= 21) {
                imgUserPhoto.setClipToOutline(true);
                if(photoPath!=""){
                    Glide.with(this).load(photoPath).into(imgUserPhoto);
                    //imgUserPhoto.setImageURI(Uri.parse(photoPath));
                }else{
                    Glide.with(this).load(R.drawable.user_icon).into(imgUserPhoto);
                    photoPath = "drawable://"+R.drawable.user_icon;
                }
            }
           // Toast.makeText(getApplicationContext(), photoPath+"2222", Toast.LENGTH_SHORT).show();

        }
    }
}
