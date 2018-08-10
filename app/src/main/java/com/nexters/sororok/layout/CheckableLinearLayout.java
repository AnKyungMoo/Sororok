package com.nexters.sororok.layout;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.Checkable;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.nexters.sororok.R;

public class CheckableLinearLayout extends LinearLayout implements Checkable {
    private boolean mIsChecked;

    public CheckableLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mIsChecked = false;
    }

    @Override
    public void setChecked(boolean b) {
        RelativeLayout rlList = findViewById(R.id.rlMemberList);
        if(mIsChecked != b){
            if(mIsChecked==false)
                rlList.setBackgroundColor(Color.rgb(40,30,50));
            else
                rlList.setBackgroundColor(Color.rgb(255,255,255));


            mIsChecked = b;
        }

        }

    @Override
    public boolean isChecked() {
        return mIsChecked;
    }

    @Override
    public void toggle() {
        setChecked(mIsChecked ? false : true);
    }
}
