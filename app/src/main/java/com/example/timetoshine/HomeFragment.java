package com.example.timetoshine;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

public class HomeFragment extends Fragment {
    private ListView listView;
    private HashMap<String, List<Pair<String, String>> > calendar = new HashMap();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            readFromCalendar(calendar);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_fragment, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView = view.findViewById(R.id.daylist_view_calendar);
        List<String> listDay = new LinkedList<>(calendar.keySet());
        //SORT BY NAME OF DAY
        listDay.sort((s1, s2) -> {
                SimpleDateFormat format = new SimpleDateFormat("EEE", Locale.ITALY);
                try {
                    Date d1 = format.parse(s1);
                    Date d2 = format.parse(s2);

                    return d1.compareTo(d2);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return 0;
        });


        listView.setAdapter(new DayAdapter(getActivity(), getContext(), calendar, listDay));
    }

    private void readFromCalendar(HashMap<String,List<Pair<String, String>> > calendar) throws IOException {
        String string = "";
        InputStream is = this.getResources().openRawResource(R.raw.calendario);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String day = "";
        while ((string = reader.readLine()) != null) {
            String[] temp = string.split("-");//0 -> time, 1 -> event
            if(temp.length == 1){
                day = temp[0];
                calendar.put(day, new LinkedList<>());
            }
            else
                calendar.get(day).add(new Pair<>(temp[0], temp[1]));
        }
        is.close();

    }


}
