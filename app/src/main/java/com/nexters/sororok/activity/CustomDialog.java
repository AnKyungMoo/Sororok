package com.nexters.sororok.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.nexters.sororok.R;

public class CustomDialog extends AlertDialog {

    private Button okBtn, cancelBtn;
    private EditText editText;
    private TextView textView;
    String title = null;

    public CustomDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        setContentView(R.layout.custom_dialog);
        initComponent();

        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }

    public void setTitle(String title){
        textView.setText(title);
    }

    public void initComponent(){
        okBtn = findViewById(R.id.btn_dialog_ok);
        cancelBtn = findViewById(R.id.btn_dialog_cancel);
        textView = findViewById(R.id.txt_dialog_message);
        editText = findViewById(R.id.edit_dialog_message);
    }
}
