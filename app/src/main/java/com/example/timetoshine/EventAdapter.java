package com.example.timetoshine;

import android.content.Context;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class EventAdapter extends BaseAdapter {
    private final LayoutInflater inflater;
    private List<Pair<String, String>> listEvent;

    public EventAdapter(Context appContext, List<Pair<String, String>> listEvent) {
        this.inflater = (LayoutInflater.from(appContext));
        this.listEvent = listEvent;
    }

    @Override
    public int getCount() {
        return listEvent.size();
    }

    @Override
    public Object getItem(int i) {
        return listEvent.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.event_listview, null);
        TextView textView_time = view.findViewById(R.id.time_event_listview);
        TextView textView_event = view.findViewById(R.id.event_event_listview);

        textView_time.setText(listEvent.get(i).first);
        textView_event.setText(listEvent.get(i).second);

        return view;
    }
}
