package com.winton.alarmclock.fragment;

import android.app.job.JobScheduler;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.winton.alarmclock.R;
import com.winton.alarmclock.widget.ClockTimerPicker;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by winton on 2016/12/19.
 */

public class AddClockFragment extends Fragment {

    private ClockTimerPicker hourPicker;
    private ClockTimerPicker minPicker;

    private List<String> listHour ;
    private List<String> listMin ;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_clock,container,false);
        hourPicker = (ClockTimerPicker) view.findViewById(R.id.ctp_hour);
        minPicker = (ClockTimerPicker)view.findViewById(R.id.ctp_min);
        initData();
        return view;
    }

    private void initData(){
        listHour = new ArrayList<>();
        for(int i=0;i<25;i++){
            if(i<10){
                listHour.add(0+""+i);
                continue;
            }
            listHour.add(i+"");
        }
        hourPicker.setData(listHour);

        listMin = new ArrayList<>();
        for(int i=0;i<60;i++){
            if(i<10){
                listMin.add(0+""+i);
                continue;
            }
            listMin.add(""+i);
        }
        minPicker.setData(listMin);

    }
}
