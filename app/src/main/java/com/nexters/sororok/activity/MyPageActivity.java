package com.nexters.sororok.activity;

import android.content.Intent;
import android.graphics.Bitmap;
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
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.nexters.sororok.R;
import com.nexters.sororok.asynctask.DownloadImageTask;
import com.nexters.sororok.asynctask.MemberInfoTask;
import com.nexters.sororok.model.MemberInfo;

import java.util.concurrent.ExecutionException;

public class MyPageActivity extends AppCompatActivity {

    private static final int REQUEST_USER_GALLERY = 1;

    private Button backBtn,logoutBtn;
    private ImageView imgUserPhoto;
    private TextView saveText;
    private String photoPath;
    private CustomDialog customDialog;
    private EditText userName, userNumber, userEmail;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page);
        initComponent();
        getMemnberInfo();
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
                if(userName.getText().length()>0 && userNumber.getText().length()>0 && userEmail.getText().length()>0){
                      Intent intent = new Intent();
                      intent.putExtra("photo_path", photoPath);
                     // intent.putExtra("user_name", userName.toString());
                      setResult(RESULT_OK, intent);
                      finish();
                }else{
                    Toast.makeText(getApplicationContext(), "빈칸을 채워주세요!",Toast.LENGTH_SHORT).show();
                }

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
        userName = findViewById(R.id.txt_user_name);
        userNumber = findViewById(R.id.txt_user_number);
        userEmail = findViewById(R.id.txt_user_email);
       /* imgUserPhoto.setBackground(new ShapeDrawable(new OvalShape()));
        if(Build.VERSION.SDK_INT >= 21) {
            imgUserPhoto.setClipToOutline(true);
        }*/

    }

    private void getMemnberInfo() {
        MemberInfoTask memberInfoTask = new MemberInfoTask();
        memberInfoTask.execute(Integer.valueOf(SplashActivity.localId));
        try {
            MemberInfo memberInfo = memberInfoTask.get();
            userName.setText(memberInfo.getName());
            userNumber.setText(memberInfo.getPhone());
            userEmail.setText(memberInfo.getEmail());
            DownloadImageTask downloadImageTask = new DownloadImageTask();

            downloadImageTask.execute("http://45.63.120.140:40005/sororok/images/" + memberInfo.getImageName());

            Bitmap myBitmap = downloadImageTask.get();
            imgUserPhoto.setImageBitmap(myBitmap);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
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
