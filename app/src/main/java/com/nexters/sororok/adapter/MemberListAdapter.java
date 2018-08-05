package com.nexters.sororok.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.nexters.sororok.R;
import com.nexters.sororok.item.MemberListItem;

import java.util.ArrayList;
import java.util.TreeSet;

public class MemberListAdapter extends BaseAdapter {

    private static final int TYPE_ITEM = 0;
    private static final int TYPE_HEADER = 1;

    private ArrayList<MemberListItem> lvMember = new ArrayList<MemberListItem>();
    private TreeSet<Integer> header = new TreeSet<Integer>();

    private LayoutInflater mInfalater;

    public MemberListAdapter(Context context){
        mInfalater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void addItem(final MemberListItem item){
        lvMember.add(item);
        notifyDataSetChanged();
    }

    public void addHeaderItem(final MemberListItem item){
        lvMember.add(item);
        header.add(lvMember.size()-1);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position){
        return header.contains(position) ? TYPE_HEADER : TYPE_ITEM;
    }

    @Override
    public int getViewTypeCount(){
        return 2;
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
        ViewHolder holder = null;
        int rowType = getItemViewType(position);

        if(convertView == null){
            holder = new ViewHolder();
            switch (rowType) {
                case TYPE_ITEM:
                    convertView = mInfalater.inflate(R.layout.member_list_item, null);
                    holder.textView = (TextView) convertView.findViewById(R.id.tvMemberList);
                    holder.imageView = (ImageView) convertView.findViewById(R.id.ivMemberList);
                    break;
                case TYPE_HEADER:
                    convertView = mInfalater.inflate(R.layout.member_list_header, null);
                    holder.textView = (TextView) convertView.findViewById(R.id.tvHeader);
                    break;
            }
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }

        if(rowType == TYPE_ITEM){
            holder.textView.setText(lvMember.get(position).getMemberName());
            holder.imageView.setImageDrawable(lvMember.get(position).getMemberProfile());
        }else if(rowType == TYPE_HEADER){
            holder.textView.setText(lvMember.get(position).getMemberName());
        }
//        final int pos = position;
//        final Context context = parent.getContext();
//
//        if(convertView==null){
//            LayoutInflater inflater  = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            convertView = inflater.inflate(R.layout.member_list_item,parent,false);
//        }
//
//        ImageView profileImageView = (ImageView) convertView.findViewById(R.id.ivMemberList);
//        TextView memberTextView = (TextView)convertView.findViewById(R.id.tvMemberList);
//
//        MemberListItem memberListItem = lvMember.get(position);
//
//        profileImageView.setImageDrawable(memberListItem.getMemberProfile());
//        memberTextView.setText(memberListItem.getMemberName());

        return convertView;
    }


    public static class ViewHolder{
        public TextView textView;
        public ImageView imageView;
    }

}

