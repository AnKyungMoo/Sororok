package com.nexters.sororok.activity;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.nexters.sororok.R;

public class CustomDailog extends Dialog {

    private Button okBtn, cancelBtn;
    private EditText editText;
    private TextView textView;

    public CustomDailog(@NonNull Context context) {
        super(context);
        initComponent();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        setContentView(R.layout.custom_dialog);
    }


    public void initComponent(){
        okBtn = findViewById(R.id.btn_dialog_ok);
        cancelBtn = findViewById(R.id.btn_dialog_cancel);
        textView = findViewById(R.id.txt_dialog_message);
    }
}
