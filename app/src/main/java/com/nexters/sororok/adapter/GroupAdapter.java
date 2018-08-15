package com.nexters.sororok.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.nexters.sororok.R;
import com.nexters.sororok.item.GroupListItem;

import java.util.ArrayList;

public class GroupAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<GroupListItem> groupListItems = new ArrayList<GroupListItem>();

    public GroupAdapter(ArrayList<GroupListItem> groupListItems) {
        this.groupListItems = groupListItems;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.group_list_item, parent, false);
        return new GroupViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        GroupViewHolder groupViewHolder = (GroupViewHolder) holder;

        groupViewHolder.signInText.setText(groupListItems.get(position).signInText);
        groupViewHolder.groupImage.setImageResource(groupListItems.get(position).groupImage);
        groupViewHolder.groupName.setText(groupListItems.get(position).groupName);
        groupViewHolder.groupBossImage.setImageResource(groupListItems.get(position).groupSmallImage);
        groupViewHolder.groupContent.setText(groupListItems.get(position).groupContent);
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

        public GroupViewHolder(View view) {
            super(view);

            signInText = (TextView) view.findViewById(R.id.txt_user_join);
            groupImage = (ImageView) view.findViewById(R.id.img_user_group);
            groupName = (TextView) view.findViewById(R.id.txt_group_maintitle);
            groupBossImage = (ImageView) view.findViewById(R.id.img_user_boss);
            groupContent = (TextView) view.findViewById(R.id.txt_group_subtitle);
            goButton = (Button) view.findViewById(R.id.btn_go_group);
        }
    }
}