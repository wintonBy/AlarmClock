package com.winton.alarmclock;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;

import com.winton.alarmclock.bean.Clock;
import com.winton.alarmclock.constant.Constant;
import com.winton.alarmclock.widget.ClockTimerPicker;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by winton on 2016/12/25.
 */

public class EditClockActivity extends AppCompatActivity implements View.OnClickListener{

    private ClockTimerPicker hourPicker;
    private ClockTimerPicker minPicker;
    private ImageButton mIBCancel;
    private ImageButton mIBConfirm;
    private CheckBox mSun;
    private CheckBox mMon;
    private CheckBox mTurs;


    private List<String> listHour ;
    private List<String> listMin ;
    private Clock mClock;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.fragment_edit_clock);
        hourPicker = (ClockTimerPicker) findViewById(R.id.ctp_hour);
        minPicker = (ClockTimerPicker)findViewById(R.id.ctp_min);
        mIBCancel = (ImageButton)findViewById(R.id.ib_edit_cancel);
        mIBConfirm = (ImageButton)findViewById(R.id.ib_edit_confirm);
        initIntent();
        initListener();
        initData();
    }
    private void initIntent(){
        int type = getIntent().getIntExtra("type", Constant.ADD_CLOCK);
        if(type == Constant.ADD_CLOCK){
            mClock = new Clock();
        }else {
            mClock = new Clock();

        }
    }
    private void initListener(){
        mIBConfirm.setOnClickListener(this);
        mIBCancel.setOnClickListener(this);
        hourPicker.setOnSelectListener(new ClockTimerPicker.OnSelectListener() {
            @Override
            public void onSelect(String value) {

            }
        });
        minPicker.setOnSelectListener(new ClockTimerPicker.OnSelectListener() {
            @Override
            public void onSelect(String value) {

            }
        });
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

    @Override
    public void onClick(View view) {
        if(view == mIBCancel){

            return;
        }
        if(view == mIBConfirm){
            return;
        }
    }
}
