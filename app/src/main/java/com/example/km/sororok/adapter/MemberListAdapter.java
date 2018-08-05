package com.example.km.sororok.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.km.sororok.R;
import com.example.km.sororok.item.MemberListItem;

import java.util.ArrayList;

public class MemberListAdapter extends BaseAdapter {

    private ArrayList<MemberListItem> lvMember = new ArrayList<MemberListItem>();

    public MemberListAdapter(){

    }

    @Override
    public int getCount() {
        return lvMember.size();
    }

    @Override
    public Object getItem(int position) {
        return lvMember.get(position);
    }

    @Override
    public long getItemId(int position ) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        if(convertView==null){
            LayoutInflater inflater  = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.member_list_item,parent,false);
        }

        ImageView profileImageView = (ImageView) convertView.findViewById(R.id.ivMemberList);
        TextView memberTextView = (TextView)convertView.findViewById(R.id.tvMemberList);

        MemberListItem memberListItem = lvMember.get(position);

        profileImageView.setImageDrawable(memberListItem.getMemberProfile());
        memberTextView.setText(memberListItem.getMemberName());

        return convertView;
    }
    public void addList(Drawable profile, String name){
        MemberListItem item = new MemberListItem();

        item.setMemberProfile(profile);
        item.setMemberName(name);

        lvMember.add(item);
        notifyDataSetChanged();
    }
}
