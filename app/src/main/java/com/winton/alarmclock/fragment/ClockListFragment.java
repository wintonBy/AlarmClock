package com.winton.alarmclock.fragment;

import android.app.AlarmManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.winton.alarmclock.MyApplication;
import com.winton.alarmclock.R;
import com.winton.alarmclock.adapter.ClockListAdapter;
import com.winton.alarmclock.entity.Clock;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by winton on 2016/12/8.
 */

public class ClockListFragment extends Fragment {

    private ListView mLV;

    private ClockListAdapter mAdapter;
    private List<Clock> mClocks;
    AlarmManager am;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_clock,container,false);
        mLV = (ListView)view.findViewById(R.id.lv_clock);
        initData();
        return view;
    }

    private void initData(){
        mClocks = new ArrayList<>();
        mAdapter = new ClockListAdapter(getActivity(),mClocks);
        mLV.setAdapter(mAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        mClocks = MyApplication.getDaoSesion().getClockDao().loadAll();
        mAdapter.update(mClocks);
    }
}
