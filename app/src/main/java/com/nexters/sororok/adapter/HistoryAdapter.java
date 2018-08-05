package com.nexters.sororok.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.nexters.sororok.R;
import com.nexters.sororok.item.HistoryListItem;

import java.util.ArrayList;

public class HistoryAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<HistoryListItem> historyContent = new ArrayList<>();
    private HistoryListItem historyListItem;

    public HistoryAdapter(Context context){
        super();
        this.context = context;
    }
    public HistoryAdapter(Context context, ArrayList<HistoryListItem> historyContent){
        this.context = context;
        this.historyContent = historyContent;
    }

    @Override
    public int getCount() {
        return historyContent.size();
    }

    @Override
    public Object getItem(int position) {
        return historyContent.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.history_item, viewGroup, false);//Inflate layout
        }
        TextView memberTextView = (TextView)view.findViewById(R.id.txt_history);
        historyListItem = historyContent.get(i);
        memberTextView.setText(historyListItem.getHistoryContent());

        return view;
    }

    public void addHistory(String history){
        historyListItem = new HistoryListItem();
        historyListItem.setHistoryContent(history);
        historyContent.add(historyListItem);
        notifyDataSetChanged();
    }
}
