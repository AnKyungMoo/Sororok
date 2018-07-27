package com.example.km.sororok.activity;
import com.example.km.sororok.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 1 && resultCode == RESULT_OK){
            Intent intent = getIntent();
            String photo_path = intent.getExtras().getString("photo_path");
            Log.i("photo_path: ", photo_path);
            Toast.makeText(getApplicationContext(),photo_path ,Toast.LENGTH_SHORT).show();
        }
    }
}
