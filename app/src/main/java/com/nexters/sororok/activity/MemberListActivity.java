package com.nexters.sororok.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.nexters.sororok.R;
import com.nexters.sororok.adapter.MemberListwithAdapter;
import com.nexters.sororok.item.MemberListItem;
import com.nexters.sororok.util.ContactsUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

/**
 * 멤버리스트 액티비티
 * 그룹을 누르면 이 액티비티에서 멤버 명단이 뜸
 * 아 개어렵겠다
 */

public class MemberListActivity extends AppCompatActivity {

    //리스트뷰를 커스텀 클래스로 정의함
    private MemberListwithAdapter listView;
    private Button groupManageBtn,backBtn,saveSelectedBtn,selectAllBtn;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_list);
        groupManageBtn = findViewById(R.id.btn_setting);
        backBtn = findViewById(R.id.btn_back);
        selectAllBtn = findViewById(R.id.btn_select_all);


        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        groupManageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent groupSettingIntent = new Intent(MemberListActivity.this, MemberSettingActivity.class);
                startActivityForResult(groupSettingIntent,400);
            }
        });

        //리스트뷰 xml 연동
        listView=findViewById(R.id.list_member);

        //리스트 중 체크된 것들이 무엇인지를 확인하는 트리셋. int형의 ID가 들어간다.
        final TreeSet<Integer> listchecked = new TreeSet<>();


        //테스트용 데이터 생성. 지금은 String으로 했지만 서버 연동 후 MemberListitem 형식으로 생성할 듯.
        final ArrayList<MemberListItem> memberList = new ArrayList<>();
        memberList.add(new MemberListItem(ContextCompat.getDrawable(this,R.drawable.blackbutton),"강문정",0,"01025557464"));
        memberList.add(new MemberListItem(ContextCompat.getDrawable(this,R.drawable.blackbutton),"곽희은",1,"01071849414"));
        memberList.add(new MemberListItem(ContextCompat.getDrawable(this,R.drawable.blackbutton),"김혜리",2,"01043232426"));
        memberList.add(new MemberListItem(ContextCompat.getDrawable(this,R.drawable.blackbutton),"남수민",3,"01032723956"));
        memberList.add(new MemberListItem(ContextCompat.getDrawable(this,R.drawable.blackbutton),"신상훈",4,"01071500894"));
        memberList.add(new MemberListItem(ContextCompat.getDrawable(this,R.drawable.blackbutton),"안경무",5,"01028175670"));
        memberList.add(new MemberListItem(ContextCompat.getDrawable(this,R.drawable.blackbutton),"한희영",6,"01098105690"));
        memberList.add(new MemberListItem(ContextCompat.getDrawable(this,R.drawable.blackbutton),"a",7,""));
        memberList.add(new MemberListItem(ContextCompat.getDrawable(this,R.drawable.blackbutton),"b",8,""));
        memberList.add(new MemberListItem(ContextCompat.getDrawable(this,R.drawable.blackbutton),"c",9,""));
        memberList.add(new MemberListItem(ContextCompat.getDrawable(this,R.drawable.blackbutton),"d",10,""));
        memberList.add(new MemberListItem(ContextCompat.getDrawable(this,R.drawable.blackbutton),"e",11,""));
        memberList.add(new MemberListItem(ContextCompat.getDrawable(this,R.drawable.blackbutton),"f",12,""));
        memberList.add(new MemberListItem(ContextCompat.getDrawable(this,R.drawable.blackbutton),"g",13,""));
        memberList.add(new MemberListItem(ContextCompat.getDrawable(this,R.drawable.blackbutton),"h",14,""));
        memberList.add(new MemberListItem(ContextCompat.getDrawable(this,R.drawable.blackbutton),"i",15,""));
        ArrayList<String> name = new ArrayList<String>();
        for(int i=0;i<memberList.size();i++){
            name.add(memberList.get(i).getMemberName());
        }
        final int namesize = name.size();


        //리스트뷰 어댑터 생성. 어댑터는 MemberListwithAdapter에 구현해 놓음.
        final MemberListwithAdapter.MemberlistAdapter mAdapter= new MemberListwithAdapter.MemberlistAdapter(this);

        //인덱스용 키워드세팅. 이걸 해놔야 인덱스가 생성된다. setAdapter 이전에 해야함. 인덱스 생성하는 김에 초성리스트를 반환받아둔다. 나중에 섹션인덱스헤더 넣을 때 써먹음.
        //여기서 데이터 정렬까지 함께 한다.
        String consonant[] = listView.setKeywordList(name);

        listView.setAdapter(mAdapter);

        //저장 버튼. 선택상황에 따라 visibility와 문구가 바뀌어야 한다. 현재는 누르면 ID값들을 토스트로 띄우게 해놓음.
        saveSelectedBtn = findViewById(R.id.btn_save_selected);
        saveSelectedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i=0;i<namesize;i++){
                    if(listchecked.contains(i)){
 //                     주소록에 번호저장 함수. 테스트하다가 저장되면 정신건강에 안좋으니 일단 주석처리
//                       ContactsUtil.savePhoneNumber(getApplicationContext(),memberList.get(i).getMemberName(),memberList.get(i).getMemberNumber());
                    }
                }
                Toast.makeText(getApplicationContext(),"저장 완료",Toast.LENGTH_SHORT).show();
            }
        });

        //전체선택 및 해제 버튼. 전체선택이 된 상태라면 전체해제하고 그 외 상황에는 전체선택한다. 저장버튼 속성 바꿔줘야함.
        selectAllBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int listSize = mAdapter.getCount();
                if(listchecked.size()!=namesize){
                    for(int i = 0 ; i<listSize; i++){
                        if(mAdapter.getItemViewType(i)==0)
                            listchecked.add((mAdapter.getItem(i).getMemberID()));
                        mAdapter.getItem(i).setChecked(true);
                    }
                    saveSelectedBtn.setVisibility(View.VISIBLE);
                    saveSelectedBtn.setText("전체 저장");
                } else {
                    for(int i =0 ; i<listSize; i++){
                        if(mAdapter.getItemViewType(i)==0)
                            listchecked.remove(mAdapter.getItem(i).getMemberID());
                        mAdapter.getItem(i).setChecked(false);
                        saveSelectedBtn.setVisibility(View.GONE);
                    }
                }
                mAdapter.notifyDataSetChanged();
            }
        });



        //리스트뷰 아이템 클릭 리스너. 다중선택이 자꾸 이상하게 먹어서 listchecked와 item의 isChecked로 이중 확인함.
        //체크 상태 변환 후 notify해주면 getview에서 색깔을 바꿔준다. 선택 상황에 따라 저장버튼 속성 바뀌어야 함.
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> adapterView, View view, int position, long l) {
                if(adapterView.getAdapter().getItemViewType(position) == 0)
                {
                    MemberListItem item = (MemberListItem) adapterView.getAdapter().getItem(position);
                    if(listchecked.contains(item.getMemberID())&&item.isChecked()==true){
                        if(listchecked.size()==namesize)
                            saveSelectedBtn.setText("선택 저장");
                        listchecked.remove(item.getMemberID());
                        item.setChecked(false);
                        mAdapter.notifyDataSetChanged();
                        if(listchecked.size()==0){
                            saveSelectedBtn.setVisibility(View.GONE);
                            }
                    } else if(item.isChecked()==false) {
                        if(listchecked.size()==0) {
                            saveSelectedBtn.setVisibility(View.VISIBLE);
                            saveSelectedBtn.setText("선택 저장");
                        }
                        listchecked.add(item.getMemberID());
                        item.setChecked(true);
                        mAdapter.notifyDataSetChanged();
                        if(listchecked.size()==namesize){
                            saveSelectedBtn.setText("전체 저장");
                        }
                    }
                } else if(adapterView.getAdapter().getItemViewType(position) == 2){
                    listView.post(new Runnable() {
                        @Override
                        public void run() {
                            listView.setSelection(0);
                        }
                    });
                }
            }
        });


        //생성한 데이터를 리스트뷰에 넣는 부분. 여기서 섹션 인덱스 헤더가 초성마다 들어간다. setKeywordList에서 받아놓은 consonant와 데이터의 초성을 비교해서 순서를 정한다.
        //wile문으로 반복해서 리스트뷰에 집어 넣는데, i가 초성 consonant[]의 인덱스, j가 데이터 name의 인덱스이다.
        int i = 0;
        int j = 0;
        while(j<name.size()) {
            String firstString =name.get(j).substring(0,1);
            char firstChar = firstString.charAt(0);
            if(isKorean(firstChar))
                firstString=Direct(firstString);
            if (firstString.equals(consonant[i])) {
                mAdapter.addHeaderItem(new MemberListItem(null, consonant[i],0));
                mAdapter.addItem(new MemberListItem(ContextCompat.getDrawable(this, R.drawable.blackbutton), name.get(j),j,memberList.get(j).getMemberNumber()));
                if(i<consonant.length-1)
                i++;
                j++;
            } else if(firstString.compareTo(consonant[i])<0){
                mAdapter.addItem(new MemberListItem(ContextCompat.getDrawable(this, R.drawable.blackbutton), name.get(j),j,memberList.get(j).getMemberNumber()));
                j++;
            } else {
                i++;
            }
        }
        mAdapter.addBottomItem(new MemberListItem(null,null,0,null));
    }

    //한글 체크 함수. MemberListwithAdapter에서도 똑같이 구현되어 있지만 보기 좋으라고 여기에 씀.
    private boolean isKorean(char ch) {
        return ch >= Integer.parseInt("AC00", 16) && ch <= Integer.parseInt("D7A3", 16);
    }

    //초성 발라내는 함수
    public String Direct(String name){
        char b =name.charAt(0);
        String chosung = null;
        int first = (b - 44032 ) / ( 21 * 28 );
        switch(first){
            case 0:
            case 1:
                chosung="ㄱ";
                break;
            case 2:
                chosung="ㄴ";
                break;
            case 3:
            case 4:
                chosung="ㄷ";
                break;
            case 5:
                chosung="ㄹ";
                break;
            case 6:
                chosung="ㅁ";
                break;
            case 7:
            case 8:
                chosung="ㅂ";
                break;
            case 9:
            case 10:
                chosung="ㅅ";
                break;
            case 11:
                chosung="ㅇ";
                break;
            case 12:
            case 13:
                chosung="ㅈ";
                break;
            case 14:
                chosung="ㅊ";
                break;
            case 15:
                chosung="ㅋ";
                break;
            case 16:
                chosung="ㅌ";
                break;
            case 17:
                chosung="ㅍ";
                break;
            case 18:
                chosung="ㅎ";
                break;

        }

        return chosung;
    }
}

