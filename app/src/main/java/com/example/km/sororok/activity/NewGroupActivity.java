package com.example.km.sororok.activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.km.sororok.R;


//그룹 생성 화면
public class NewGroupActivity extends AppCompatActivity {

    private static final int REQUEST_USER_GALLERY = 1;
    private ImageView imgGroupPhoto;

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
    }

    public void initComponent(){
        imgGroupPhoto = (ImageView)findViewById(R.id.img_new_group);
    }
}
