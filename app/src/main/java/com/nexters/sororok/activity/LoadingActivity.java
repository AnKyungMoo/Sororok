package com.nexters.sororok.activity;

import android.content.Intent;
import android.os.AsyncTask;
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
    TextView white, pink;
    int j,k;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        white= findViewById(R.id.tvLoadWhite);
        pink=findViewById(R.id.tvLoadPink);

        ArrayList<String> namelist=new ArrayList<>();
        ArrayList<String> numberlist=new ArrayList<>();

        Intent intent = getIntent();
        ArrayList<MemberListItem> memberListSort=new ArrayList<>();
        memberListSort.addAll((ArrayList<MemberListItem>) intent.getSerializableExtra("savelist"));
        TreeSet<Integer> listchecked =new TreeSet<Integer>();
        listchecked.addAll((TreeSet<Integer>)intent.getSerializableExtra("checklist"));

        j=listchecked.size();
        ArrayList<String> myMember = ContactsUtil.getNumberList(getApplicationContext());
        for(int i=0;i<memberListSort.size();i++){
            if(listchecked.contains(i)&&!(myMember.contains(memberListSort.get(i).getMemberNumber()))){
                namelist.add(memberListSort.get(i).getMemberName());
                numberlist.add(memberListSort.get(i).getMemberNumber());
//              ContactsUtil.savePhoneNumber(getApplicationContext(),memberListSort.get(i).getMemberName(),memberListSort.get(i).getMemberNumber());
            }
            k=namelist.size();
            pink.setText(valueOf(k));
        }
        new SaveTask().execute(namelist,numberlist);

    }

    public class SaveTask extends AsyncTask<ArrayList<String>,Integer,Integer> {

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
        }

        @Override
        protected Integer doInBackground(ArrayList<String>...arrayLists) {
            for(int i=0;i<arrayLists[0].size();i++){
                ContactsUtil.savePhoneNumber(getApplicationContext(),arrayLists[0].get(i),arrayLists[1].get(i));
                publishProgress(i+1);
            }
            return arrayLists[0].size();
        }
        @Override
        protected void onProgressUpdate(Integer... params){
            white.setText(valueOf(params[0]));
        }

        protected void onPostExecute(Integer result){
            super.onPostExecute(result);
            Toast.makeText(getApplicationContext(),"전체 "+j+"명 중"+k+"명이 저장되었습니다.",Toast.LENGTH_SHORT);
            finish();

        }
    }
}


