package com.nexters.sororok.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nexters.sororok.R;
import com.nexters.sororok.adapter.MemberListwithAdapter;
import com.nexters.sororok.item.ContactItem;
import com.nexters.sororok.item.MemberListItem;
import com.nexters.sororok.util.ContactsUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.TreeSet;

/**
 * 멤버리스트 액티비티
 * 그룹을 누르면 이 액티비티에서 멤버 명단이 뜸
 * 아 개어렵겠다
 */

public class MemberBanActivity extends AppCompatActivity {

    //리스트뷰를 커스텀 클래스로 정의함
    private MemberListwithAdapter listView;
    private Button groupManageBtn,backBtn,saveSelectedBtn, selectedNumberBtn;
    private EditText etSearchMem;
    private ImageView ivSearch;
    private RelativeLayout rlOnFail;
    private InputMethodManager imm;
    public String[] consonant;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_ban);
        groupManageBtn = findViewById(R.id.btn_setting);
        backBtn = findViewById(R.id.btn_back);
        rlOnFail=findViewById(R.id.rlIfFail);
        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        Intent intent = getIntent();
        int id = intent.getIntExtra("agroupid",-1);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        groupManageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            //서버에 삭제 알림
            }
        });

        //리스트뷰 xml 연동
        listView=findViewById(R.id.list_member);

        //리스트 중 체크된 것들이 무엇인지를 확인하는 트리셋. int형의 ID가 들어간다.
        final TreeSet<Integer> listchecked = new TreeSet<>();


        //테스트용 데이터 생성. 지금은 String으로 했지만 서버 연동 후 MemberListitem 형식으로 생성할 듯.
        final ArrayList<MemberListItem> memberList = new ArrayList<>();

        memberList.add(new MemberListItem(null,"가스트1",0,"01011114999"));
        memberList.add(new MemberListItem(null,"나스트2",1,"01011112320"));
        memberList.add(new MemberListItem(null,"다스트3",2,"01011119150"));
        memberList.add(new MemberListItem(null,"라스트4",3,"01011119332"));
        memberList.add(new MemberListItem(null,"마스트5",4,"01011115611"));
        memberList.add(new MemberListItem(null,"바스트6",5,"01011116786"));
        memberList.add(new MemberListItem(null,"사스트7",6,"01011117773"));
        memberList.add(new MemberListItem(null,"자스트8",7,"01011113603"));
        memberList.add(new MemberListItem(null,"차스트9",8,"01011116290"));
        memberList.add(new MemberListItem(null,"카스트10",9,"01011116040"));
        memberList.add(new MemberListItem(null,"타스트11",10,"01011116251"));
        memberList.add(new MemberListItem(null,"테스트12",11,"01011116351"));
        memberList.add(new MemberListItem(null,"테스트13",12,"01011112033"));
        memberList.add(new MemberListItem(null,"테스트14",13,"01011115531"));
        memberList.add(new MemberListItem(null,"테스트15",14,"01011116889"));
        memberList.add(new MemberListItem(null,"테스트16",15,"01011116402"));
        memberList.add(new MemberListItem(null,"테스트17",16,"01011118951"));
        memberList.add(new MemberListItem(null,"테스트18",17,"01011114199"));
        memberList.add(new MemberListItem(null,"테스트19",18,"01011116580"));
        memberList.add(new MemberListItem(null,"테스트20",19,"01011113853"));
        memberList.add(new MemberListItem(null,"테스트21",20,"01011114504"));
        memberList.add(new MemberListItem(null,"테스트22",21,"01011116238"));
        memberList.add(new MemberListItem(null,"테스트23",22,"01011113256"));
        memberList.add(new MemberListItem(null,"테스트24",23,"01011113464"));
        memberList.add(new MemberListItem(null,"테스트25",24,"01011119305"));
        memberList.add(new MemberListItem(null,"테스트26",25,"01011115932"));
        memberList.add(new MemberListItem(null,"테스트27",26,"01011118638"));
        memberList.add(new MemberListItem(null,"테스트28",27,"01011113810"));
        memberList.add(new MemberListItem(null,"테스트29",28,"01011114880"));
        memberList.add(new MemberListItem(null,"테스트30",29,"01011118035"));
        memberList.add(new MemberListItem(null,"테스트31",30,"01011112877"));
        memberList.add(new MemberListItem(null,"테스트32",31,"01011115802"));
        memberList.add(new MemberListItem(null,"테스트33",32,"01011119232"));
        memberList.add(new MemberListItem(null,"테스트34",33,"01011118675"));
        memberList.add(new MemberListItem(null,"테스트35",34,"01011116566"));
        memberList.add(new MemberListItem(null,"테스트36",35,"01011118374"));
        memberList.add(new MemberListItem(null,"테스트37",36,"01011116784"));
        memberList.add(new MemberListItem(null,"테스트38",37,"01011116416"));
        memberList.add(new MemberListItem(null,"테스트39",38,"01011119959"));
        memberList.add(new MemberListItem(null,"테스트40",39,"01011115459"));
        memberList.add(new MemberListItem(null,"테스트41",40,"01011115090"));
        memberList.add(new MemberListItem(null,"테스트42",41,"01011115718"));
        memberList.add(new MemberListItem(null,"테스트43",42,"01011112314"));
        memberList.add(new MemberListItem(null,"테스트44",43,"01011118026"));
        memberList.add(new MemberListItem(null,"테스트45",44,"01011112641"));
        memberList.add(new MemberListItem(null,"테스트46",45,"01011111590"));
        memberList.add(new MemberListItem(null,"테스트47",46,"01011111629"));
        memberList.add(new MemberListItem(null,"테스트48",47,"01011117302"));
        memberList.add(new MemberListItem(null,"테스트49",48,"01011119088"));
        memberList.add(new MemberListItem(null,"테스트50",49,"01011117924"));
        memberList.add(new MemberListItem(null,"테스트51",50,"01011117634"));
        memberList.add(new MemberListItem(null,"테스트52",51,"01011112170"));
        memberList.add(new MemberListItem(null,"테스트53",52,"01011112464"));
        memberList.add(new MemberListItem(null,"테스트54",53,"01011119725"));
        memberList.add(new MemberListItem(null,"테스트55",54,"01011111195"));
        memberList.add(new MemberListItem(null,"테스트56",55,"01011119139"));
        memberList.add(new MemberListItem(null,"테스트57",56,"01011113818"));
        memberList.add(new MemberListItem(null,"테스트58",57,"01011117827"));
        memberList.add(new MemberListItem(null,"테스트59",58,"01011112264"));
        memberList.add(new MemberListItem(null,"테스트60",59,"01011119713"));
        memberList.add(new MemberListItem(null,"테스트61",60,"01011112335"));
        memberList.add(new MemberListItem(null,"테스트62",61,"01011113019"));
        memberList.add(new MemberListItem(null,"테스트63",62,"01011117534"));
        memberList.add(new MemberListItem(null,"테스트64",63,"01011118564"));
        memberList.add(new MemberListItem(null,"테스트65",64,"01011111303"));
        memberList.add(new MemberListItem(null,"테스트66",65,"01011115075"));
        memberList.add(new MemberListItem(null,"테스트67",66,"01011116901"));
        memberList.add(new MemberListItem(null,"테스트68",67,"01011119377"));
        memberList.add(new MemberListItem(null,"테스트69",68,"01011118965"));
        memberList.add(new MemberListItem(null,"테스트70",69,"01011116263"));
        memberList.add(new MemberListItem(null,"테스트71",70,"01011115237"));
        memberList.add(new MemberListItem(null,"테스트72",71,"01011117752"));
        memberList.add(new MemberListItem(null,"테스트73",72,"01011117763"));
        memberList.add(new MemberListItem(null,"테스트74",73,"01011111798"));
        memberList.add(new MemberListItem(null,"테스트75",74,"01011118130"));
        memberList.add(new MemberListItem(null,"테스트76",75,"01011117601"));
        memberList.add(new MemberListItem(null,"테스트77",76,"01011112024"));
        memberList.add(new MemberListItem(null,"테스트78",77,"01011111423"));
        memberList.add(new MemberListItem(null,"테스트79",78,"01011119105"));
        memberList.add(new MemberListItem(null,"테스트80",79,"01011118467"));
        memberList.add(new MemberListItem(null,"테스트81",80,"01011114198"));
        memberList.add(new MemberListItem(null,"테스트82",81,"01011119761"));
        memberList.add(new MemberListItem(null,"테스트83",82,"01011119537"));
        memberList.add(new MemberListItem(null,"테스트84",83,"01011111576"));
        memberList.add(new MemberListItem(null,"테스트85",84,"01011112652"));
        memberList.add(new MemberListItem(null,"테스트86",85,"01011112924"));
        memberList.add(new MemberListItem(null,"테스트87",86,"01011119530"));
        memberList.add(new MemberListItem(null,"테스트88",87,"01011118107"));
        memberList.add(new MemberListItem(null,"테스트89",88,"01011116983"));
        memberList.add(new MemberListItem(null,"테스트90",89,"01011117256"));
        memberList.add(new MemberListItem(null,"테스트91",90,"01011114277"));
        memberList.add(new MemberListItem(null,"테스트92",91,"01011116238"));
        memberList.add(new MemberListItem(null,"테스트93",92,"01011112494"));
        memberList.add(new MemberListItem(null,"테스트94",93,"01011115492"));
        memberList.add(new MemberListItem(null,"테스트95",94,"01011115919"));
        memberList.add(new MemberListItem(null,"테스트96",95,"01011111119"));
        memberList.add(new MemberListItem(null,"테스트97",96,"01011111217"));
        memberList.add(new MemberListItem(null,"테스트98",97,"01011116772"));
        memberList.add(new MemberListItem(null,"파스트99",98,"01011114251"));
        memberList.add(new MemberListItem(null,"하스트100",99,"01011119029"));


        final ArrayList<String> name = new ArrayList<String>();
        for(int i=0;i<memberList.size();i++){
            name.add(memberList.get(i).getMemberName());
        }
        final int namesize = name.size();


        //리스트뷰 어댑터 생성. 어댑터는 MemberListwithAdapter에 구현해 놓음.
        final MemberListwithAdapter.MemberlistAdapter mAdapter= new MemberListwithAdapter.MemberlistAdapter(this);

        //인덱스용 키워드세팅. 이걸 해놔야 인덱스가 생성된다. setAdapter 이전에 해야함. 인덱스 생성하는 김에 초성리스트를 반환받아둔다. 나중에 섹션인덱스헤더 넣을 때 써먹음.
        //여기서 데이터 정렬까지 함께 한다.
        consonant = listView.setKeywordList(name);

        final ArrayList<MemberListItem> memberListSort = new ArrayList<>();
        for(int i =0;i<namesize;i++){
            for(int j =0;j<namesize;j++){
                if(name.get(i).equals(memberList.get(j).getMemberName())){
                    memberListSort.add(memberList.get(j));
                    break;
                }
            }
        }
        listView.setAdapter(mAdapter);


        selectedNumberBtn=findViewById(R.id.btn_select_number);

        //저장 버튼. 선택상황에 따라 visibility와 문구가 바뀌어야 한다. 현재는 누르면 ID값들을 토스트로 띄우게 해놓음.
        saveSelectedBtn = findViewById(R.id.btn_save_selected);
        saveSelectedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mAdapter.clearAdapter();
                listView.clearKeyword();
                int count =0;
                for(int i=0;i<memberListSort.size();i++){
                    if(listchecked.contains(memberListSort.get(i).getMemberID())){
                        memberListSort.remove(i);
                        name.remove(i);
                        count++;
                    }
                    consonant=listView.setKeywordList(name);
                }
                listchecked.clear();
                setBasic(name,consonant,mAdapter,memberListSort);
                selectedNumberBtn.setText("0");
                selectedNumberBtn.setVisibility(View.GONE);
                saveSelectedBtn.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(),"총 "+count+"명 삭제 완료",Toast.LENGTH_SHORT).show();
            }
        });


        etSearchMem=findViewById(R.id.etSearchMember);
        imm.hideSoftInputFromWindow(etSearchMem.getWindowToken(), 0);
        etSearchMem.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if(actionId== EditorInfo.IME_ACTION_SEARCH){
                    imm.hideSoftInputFromWindow(etSearchMem.getWindowToken(), 0);
                    listchecked.clear();
                    String etText = etSearchMem.getText().toString();
                    mAdapter.clearAdapter();
                    listView.clearKeyword();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            rlOnFail.setVisibility(View.GONE);
                        }
                    });
                    if(etText.length()==0){
                        setBasic(name,consonant,mAdapter,memberListSort);
                    }
                    else {
                        for(int i =0;i<memberListSort.size();i++){
                            if(memberListSort.get(i).getMemberName().contains(etText)){
                                mAdapter.addItem(new MemberListItem(null, name.get(i),memberListSort.get(i).getMemberID(),memberListSort.get(i).getMemberNumber()));
                            }
                        }
                        if(mAdapter.getCount()==0){
                            (MemberBanActivity.this).runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    rlOnFail.setVisibility(View.VISIBLE);
                                }
                            });
                        }else{
                            mAdapter.addBottomItem(new MemberListItem(null,null,0,null));
                        }
                    }
                    (MemberBanActivity.this).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mAdapter.notifyDataSetChanged();
                        }
                    });
                }
                return false;
            }
        });
        ivSearch=findViewById(R.id.ivSearchButton);
        ivSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imm.hideSoftInputFromWindow(etSearchMem.getWindowToken(), 0);
                listchecked.clear();
                String etText = etSearchMem.getText().toString();
                mAdapter.clearAdapter();
                listView.clearKeyword();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        rlOnFail.setVisibility(View.GONE);
                    }
                });
                if(etText.length()==0){
                    setBasic(name,consonant,mAdapter,memberListSort);
                }
                else {
                    for(int i =0;i<memberListSort.size();i++){
                        if(memberListSort.get(i).getMemberName().contains(etText)){
                            mAdapter.addItem(new MemberListItem(null, name.get(i),memberListSort.get(i).getMemberID(),memberListSort.get(i).getMemberNumber()));
                        }
                    }
                    if(mAdapter.getCount()==0){
                        (MemberBanActivity.this).runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                rlOnFail.setVisibility(View.VISIBLE);
                            }
                        });
                    }else{
                        mAdapter.addBottomItem(new MemberListItem(null,null,0,null));
                    }
                }
                (MemberBanActivity.this).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter.notifyDataSetChanged();
                    }
                });
            }
        });


        //리스트뷰 아이템 클릭 리스너. 다중선택이 자꾸 이상하게 먹어서 listchecked와 item의 isChecked로 이중 확인함.
        //체크 상태 변환 후 notify해주면 getview에서 색깔을바꿔준다. 선택 상황에 따라 저장버튼 속성 바뀌어야 함.
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> adapterView, View view, int position, long l) {
                if(adapterView.getAdapter().getItemViewType(position) == 0||adapterView.getAdapter().getItemViewType(position) == 3)
                {
                    MemberListItem item = (MemberListItem) adapterView.getAdapter().getItem(position);
                    if(listchecked.contains(item.getMemberID())&&item.isChecked()==true){
//                            if(listchecked.size()==namesize)
//                                saveSelectedBtn.setText("선택 저장");
                        listchecked.remove(item.getMemberID());
                        selectedNumberBtn.setText(String.valueOf(listchecked.size()));
                        item.setChecked(false);
                        mAdapter.notifyDataSetChanged();
                        if(listchecked.size()==0){
                            saveSelectedBtn.setVisibility(View.GONE);
                            selectedNumberBtn.setVisibility(View.GONE);
                        }
                    } else if(!listchecked.contains(item.getMemberID())) {
                        if(listchecked.size()==0) {
                            saveSelectedBtn.setVisibility(View.VISIBLE);
                            selectedNumberBtn.setVisibility(View.VISIBLE);
                        }
                        listchecked.add(item.getMemberID());
                        selectedNumberBtn.setText(String.valueOf(listchecked.size()));
                        item.setChecked(true);
                        mAdapter.notifyDataSetChanged();
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


        setBasic(name,consonant,mAdapter,memberListSort);

    }

    public void setBasic(ArrayList<String>name, String[] consonant, MemberListwithAdapter.MemberlistAdapter mAdapter,ArrayList<MemberListItem> memberList){
        //생성한 데이터를 리스트뷰에 넣는 부분. 여기서 섹션 인덱스 헤더가 초성마다 들어간다. setKeywordList에서 받아놓은 consonant와 데이터의 초성을 비교해서 순서를 정한다.
        //wile문으로 반복해서 리스트뷰에 집어 넣는데, i가 초성 consonant[]의 인덱스, j가 데이터 name의 인덱스이다.
        int i = 0;
        int j = 0;
        String k = "";
        while(j<name.size()) {
            String firstString =name.get(j).substring(0,1);
            char firstChar = firstString.charAt(0);
            if(isKorean(firstChar))
                firstString=Direct(firstString);
            if (firstString.equals(consonant[i])) {
                if(k!=consonant[i]){
                    mAdapter.addHeaderItem(new MemberListItem(null, consonant[i]+".",0));
                    k=consonant[i];
                }
                mAdapter.addItem(new MemberListItem(null, name.get(j),memberList.get(j).getMemberID(),memberList.get(j).getMemberNumber()));
                if(i<consonant.length-1)
                    i++;
                j++;
            } else if(firstString.compareTo(consonant[i])<0){
                mAdapter.addItem(new MemberListItem(null, name.get(j),memberList.get(j).getMemberID(),memberList.get(j).getMemberNumber()));
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

