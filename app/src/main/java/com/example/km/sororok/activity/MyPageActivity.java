package com.example.km.sororok.activity;
import com.example.km.sororok.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class MyPageActivity extends AppCompatActivity {

    private static final int REQUEST_USER_GALLERY = 1;

    private ImageView imgUserPhoto;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page);
        initComponent();

        imgUserPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MyPageActivity.this, UserGalleryActivity.class);
                startActivityForResult(i, REQUEST_USER_GALLERY);

            }
        });
    }

    public void initComponent(){
        imgUserPhoto = (ImageView)findViewById(R.id.img_user_photo);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
    }
}
