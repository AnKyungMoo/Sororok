package com.example.km.sororok.activity;
import com.example.km.sororok.R;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class MyPageActivity extends AppCompatActivity {

    ImageView imgUserPhoto;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page);
        imgUserPhoto = (ImageView)findViewById(R.id.img_user_photo);
    }
}
