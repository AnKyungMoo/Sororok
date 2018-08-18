package com.nexters.sororok.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.nexters.sororok.R;

public class CustomDialog extends AlertDialog{

    private Button okBtn, cancelBtn;
    private EditText editText;
    private TextView textView;
    private int flag = 1;

    public CustomDialog(@NonNull Context context) {
        super(context);
    }

    /*public CustomDialog(Context context){
        super(context);
    }*/


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
                if(getFlag() == 1){
                    Intent intent = new Intent(view.getContext(), MemberListActivity.class);
                    view.getContext().startActivity(intent);
                    //activity.startActivity(intent);
                }else
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


    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public void setTitle(String title){
        textView.setText(title);
    }

    public EditText getEditText() {
        return editText;
    }

    public void setEditText(EditText editText) {
        this.editText = editText;
    }

    public void initComponent(){
        okBtn = findViewById(R.id.btn_dialog_ok);
        cancelBtn = findViewById(R.id.btn_dialog_cancel);
        textView = findViewById(R.id.txt_dialog_message);
        editText = findViewById(R.id.edit_dialog_message);
    }
}
