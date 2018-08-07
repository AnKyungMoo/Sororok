package com.nexters.sororok.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import com.nexters.sororok.R;

public class LoginInfoActivity extends AppCompatActivity {

    ImageButton configButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_info);

        configButton = findViewById(R.id.login_config_button);

        configButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginInfoActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
