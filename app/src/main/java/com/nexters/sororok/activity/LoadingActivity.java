package com.nexters.sororok.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.nexters.sororok.R;
import com.nexters.sororok.item.MemberListItem;
import com.nexters.sororok.util.ContactsUtil;

import java.util.ArrayList;
import java.util.TreeSet;

import static java.lang.String.valueOf;

public class LoadingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        TextView white, pink;

        white= findViewById(R.id.tvLoadWhite);
        pink=findViewById(R.id.tvLoadPink);

        Intent intent = getIntent();
        ArrayList<MemberListItem> memberListSort=new ArrayList<>();
        memberListSort.addAll((ArrayList<MemberListItem>) intent.getSerializableExtra("savelist"));
        TreeSet<Integer> listchecked =new TreeSet<Integer>();
        listchecked.addAll((TreeSet<Integer>)intent.getSerializableExtra("checklist"));
        pink.setText(valueOf(listchecked.size()));
        int j=0;
        ArrayList<String> myMember = ContactsUtil.getNumberList(getApplicationContext());
        for(int i=0;i<memberListSort.size();i++){
            if(listchecked.contains(i)&&!(myMember.contains(memberListSort.get(i).getMemberNumber()))){
              ContactsUtil.savePhoneNumber(getApplicationContext(),memberListSort.get(i).getMemberName(),memberListSort.get(i).getMemberNumber());
              j++;
              white.setText(valueOf(j));
            }
        }
        Toast.makeText(getApplicationContext(),j+"개 저장 완료",Toast.LENGTH_SHORT).show();
        finish();
    }

    
}
