package com.nexters.sororok.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.nexters.sororok.R;

import java.util.ArrayList;

public class HistoryAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<String> historyContent = new ArrayList<>();

    public HistoryAdapter(Context context){
        super();
        this.context = context;
    }
    public HistoryAdapter(Context context, ArrayList<String> historyContent){
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

        return view;
    }

    public void addHistory(String history){
        historyContent.add(history);
        notifyDataSetChanged();
    }
}
