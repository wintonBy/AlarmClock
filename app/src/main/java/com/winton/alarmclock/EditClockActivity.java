package com.winton.alarmclock;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.winton.alarmclock.constant.Constant;
import com.winton.alarmclock.entity.Clock;
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
    private LinearLayout mLLDays;
    private CheckBox mSun;
    private CheckBox mMon;
    private CheckBox mTue;
    private CheckBox mWed;
    private CheckBox mThurs;
    private CheckBox mFri;
    private CheckBox mSat;
    private TextView mTVRepeatMode;
    private TextView mTVTag;


    private List<String> listHour ;
    private List<String> listMin ;
    private Clock mClock;

    private static final int  REQ_CODE_TAG = 0;
    private static final int REQ_CODE_REPEAT = 1;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getSupportActionBar().hide();
        setContentView(R.layout.fragment_edit_clock);
        hourPicker = (ClockTimerPicker) findViewById(R.id.ctp_hour);
        minPicker = (ClockTimerPicker)findViewById(R.id.ctp_min);
        mIBCancel = (ImageButton)findViewById(R.id.ib_edit_cancel);
        mIBConfirm = (ImageButton)findViewById(R.id.ib_edit_confirm);
        mSun = (CheckBox)findViewById(R.id.cb_sun);
        mMon = (CheckBox)findViewById(R.id.cb_mon);
        mTue = (CheckBox)findViewById(R.id.cb_tue);
        mWed = (CheckBox)findViewById(R.id.cb_wed);
        mThurs = (CheckBox)findViewById(R.id.cb_thurs);
        mFri = (CheckBox)findViewById(R.id.cb_fri);
        mSat = (CheckBox)findViewById(R.id.cb_sat);
        mLLDays = (LinearLayout)findViewById(R.id.ll_days);
        mTVRepeatMode = (TextView)findViewById(R.id.tv_edit_repeat);
        mTVTag = (TextView)findViewById(R.id.tv_edit_tag);
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
        mTVRepeatMode.setOnClickListener(this);
        mTVTag.setOnClickListener(this);
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
            cancelEdit();
            return;
        }
        if(view == mIBConfirm){
            saveEdit();
            return;
        }
        if(view == mTVRepeatMode){
            Intent intent = new Intent(EditClockActivity.this,CommonInputActivity.class);
            intent.putExtra("from",0);
            startActivityForResult(intent,REQ_CODE_REPEAT);
            return;
        }
        if(view == mTVTag){
            Intent intent = new Intent(EditClockActivity.this,CommonInputActivity.class);
            intent.putExtra("from",1);
            startActivityForResult(intent,REQ_CODE_TAG);
            return;
        }
    }

    private void cancelEdit(){
        EditClockActivity.this.finish();
    }
    private void saveEdit(){
        mClock.setSun(mSun.isChecked());
        mClock.setMon(mMon.isChecked());
        mClock.setTues(mTue.isChecked());
        mClock.setWed(mWed.isChecked());
        mClock.setThurs(mThurs.isChecked());
        mClock.setFri(mFri.isChecked());
        mClock.setSat(mSat.isChecked());
        mClock.setMinute(minPicker.getValue());
        mClock.setHour(hourPicker.getValue());
        mClock.setTitle(mTVTag.getText().toString().trim());
        MyApplication.getDaoSesion().getClockDao().save(mClock);
        this.finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_CANCELED){
            return;
        }
        switch (requestCode){
            case REQ_CODE_REPEAT:
                int repeat = data.getIntExtra("repeat",-1);
                mTVRepeatMode.setTag(repeat);
                dealDayChooseText(data);
                break;
            case REQ_CODE_TAG:
                String tagValue = data.getStringExtra("tag");
                mTVTag.setText(""+tagValue);
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    private void dealDayChooseText(Intent data){
        mMon.setChecked(data.getBooleanExtra("one",false));
        mTue.setChecked(data.getBooleanExtra("two",false));
        mWed.setChecked(data.getBooleanExtra("three",false));
        mThurs.setChecked(data.getBooleanExtra("four",false));
        mFri.setChecked(data.getBooleanExtra("five",false));
        mSat.setChecked(data.getBooleanExtra("six",false));
        mSun.setChecked(data.getBooleanExtra("seven",false));
    }
}
