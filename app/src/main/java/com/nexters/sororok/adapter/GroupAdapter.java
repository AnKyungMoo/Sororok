package com.nexters.sororok.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nexters.sororok.R;
import com.nexters.sororok.activity.CustomDialog;
import com.nexters.sororok.asynctask.DownloadImageTask;
import com.nexters.sororok.item.GroupListItem;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class GroupAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<GroupListItem> groupListItems;
    private CustomDialog customDialog;

    private Activity activity;

    public GroupAdapter(ArrayList<GroupListItem> groupListItems, Activity activity) {
        this.groupListItems = groupListItems;
        this.activity=activity;
        //this.context = context;
        //customDialog = new CustomDialog(context);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.group_list_item, parent, false);
        return new GroupViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        final GroupViewHolder groupViewHolder = (GroupViewHolder) holder;

       // groupViewHolder.isJoinText.setText(groupListItems.get(position).isJoinText);
        groupViewHolder.signInText.setText(groupListItems.get(position).signInText);


        if (groupListItems.get(position).groupImage != null) {
            try {
                DownloadImageTask downloadImageTask = new DownloadImageTask();

                downloadImageTask.execute("http://45.63.120.140:40005/sororok/images/" + groupListItems.get(position).groupImage);

                Bitmap myBitmap = downloadImageTask.get();
                groupViewHolder.groupImage.setImageBitmap(myBitmap);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        else {
            groupViewHolder.groupImage.setImageResource(R.drawable.combined_shape_rectangle_2_path_rectangle_copy_oval_copy_3_oval_copy_5_mask_copy_copy_copy_copy);
        }

        groupViewHolder.groupName.setText(groupListItems.get(position).groupName);
       // groupViewHolder.groupBossImage.setImageResource(groupListItems.get(position).groupSmallImage);
        groupViewHolder.groupContent.setText(groupListItems.get(position).groupContent);
        groupViewHolder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
//                customDialog = new CustomDialog(view.getContext(), groupListItems.get(position).groupId);
//                customDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAni;
//                customDialog.setFlag(1);
//                customDialog.show();
//                customDialog.getEditText().setVisibility(View.VISIBLE);
//                customDialog.setTitle("그룹코드를 입력해주세요.");


                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                final EditText edit = new EditText(activity);
                builder.setView(edit);
                builder.setMessage("그룹 코드를 입력해 주세요");

                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        // Text 값 받아서 로그 남기기
                        String value = edit.getText().toString();
                        Toast.makeText(view.getContext(),value,Toast.LENGTH_SHORT).show();
                        dialog.dismiss();     //닫기
                        // Event
                    }
                });

// 취소 버튼 설정
                builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();     //닫기
                        // Event
                    }
                });

// 창 띄우기
                builder.show();

                /*customDialog.getEditText().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Toast.makeText(view.getContext(), "클릭",Toast.LENGTH_SHORT).show();
                        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(INPUT_METHOD_SERVICE);
                        imm.showSoftInput(customDialog.getEditText(), 0);
                        customDialog.getEditText().setFocusable(true);
                        customDialog.setTitle("그룹코드를 입력해주세요.");
                    }
                });*/
                //customDialog.getEditText().setEnabled(true);

            }
        });

       /* groupViewHolder.goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customDialog = new CustomDialog(view.getContext());
                customDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAni;
                customDialog.show();
                customDialog.setFlag(1);
                customDialog.getEditText().setVisibility(View.VISIBLE);
                customDialog.setTitle("그룹코드를 입력해주세요.");


                *//*Intent intent = new Intent (view.getContext(), MemberListActivity.class);
                view.getContext().startActivity(intent);*//*
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return groupListItems.size();
    }

    public static class GroupViewHolder extends RecyclerView.ViewHolder {

        private TextView signInText;
        private ImageView groupImage;
        private TextView groupName;
        private ImageView groupBossImage;
        private TextView groupContent;
        private Button goButton;
        private RelativeLayout relativeLayout;
        private TextView isJoinText;

        public GroupViewHolder(View view) {
            super(view);
           // isJoinText = (TextView)view.findViewById(R.id.txt_user_join);
            signInText = (TextView) view.findViewById(R.id.txt_user_join);
            groupImage = (ImageView) view.findViewById(R.id.img_user_group);
            groupName = (TextView) view.findViewById(R.id.txt_group_maintitle);
           // groupBossImage = (ImageView) view.findViewById(R.id.img_user_boss);
            groupContent = (TextView) view.findViewById(R.id.txt_group_subtitle);
            relativeLayout = view.findViewById(R.id.click_member_list); //셀 전체
           // goButton = (Button) view.findViewById(R.id.btn_go_group);
        }
    }
}
