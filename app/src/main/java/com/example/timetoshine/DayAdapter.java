package com.example.timetoshine;

import android.app.Activity;
import android.content.Context;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class DayAdapter extends BaseAdapter {
    private final LayoutInflater inflater;
    private HashMap<String, List<Pair<String, String>> > list;
    private List<String> day;
    private Context appContext;

    public DayAdapter(Activity activity, Context applicationContext, HashMap<String, List<Pair<String, String>> > list, List<String> day){
        this.inflater = (LayoutInflater.from(applicationContext));
        this.appContext = applicationContext;
        this.list = list;
        this.day = day;
    }


    @Override
    public int getCount() {
        return day.size();
    }

    @Override
    public Object getItem(int i) {
        return day.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.dayevent_listview, null);
        TextView textView_day = view.findViewById(R.id.day_calendar);
        ListView listView = view.findViewById(R.id.list_view_calendar);
        textView_day.setText(String.valueOf(day.get(i)));

        view.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));

        view.getLayoutParams().height = 250 * list.get(day.get(i)).size();


        listView.setAdapter(new EventAdapter(appContext, list.get(day.get(i))));


        return view;
    }
}
