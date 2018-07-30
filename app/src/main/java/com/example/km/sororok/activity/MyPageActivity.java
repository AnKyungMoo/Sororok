package com.example.km.sororok.activity;

import android.content.Intent;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.km.sororok.R;

public class MyPageActivity extends AppCompatActivity {

    private static final int REQUEST_USER_GALLERY = 1;

    private ImageView imgUserPhoto;
    private String photoPath;

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
    }

    public void initComponent(){
        imgUserPhoto = (ImageView)findViewById(R.id.img_user_photo);
        imgUserPhoto.setBackground(new ShapeDrawable(new OvalShape()));
        if(Build.VERSION.SDK_INT >= 21) {
            imgUserPhoto.setClipToOutline(true);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && resultCode == RESULT_OK) {
            photoPath = data.getStringExtra("photo_path");
            if(photoPath!=null){
                Glide.with(this).load(photoPath).into(imgUserPhoto);
                //imgUserPhoto.setImageURI(Uri.parse(photoPath));
            }
        }
    }
}
