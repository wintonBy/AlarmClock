package com.winton.alarmclock;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by winton on 2017/2/25.
 */

public class CommonInputActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText mETCommon;
    private LinearLayout mLLRepeat;
    private TextView mTVTitle;
    private FrameLayout mFLBack;
    private FrameLayout mFLOne;
    private FrameLayout mFLTwo;
    private FrameLayout mFLThree;
    private FrameLayout mFLFour;
    private FrameLayout mFLFive;
    private FrameLayout mFLSix;
    private FrameLayout mFLSeven;

    private int from;
    private static int FC_REPEAT = 0;
    private static int FC_TAG = 1;
    private boolean one;
    private boolean two;
    private boolean three;
    private boolean four;
    private boolean five;
    private boolean six;
    private boolean seven;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common);
        mETCommon = (EditText)findViewById(R.id.et_common_input);
        mLLRepeat = (LinearLayout)findViewById(R.id.ll_repeat);
        mTVTitle = (TextView)findViewById(R.id.tv_common_title);
        mFLBack = (FrameLayout) findViewById(R.id.fl_back);
        mFLOne = (FrameLayout)findViewById(R.id.fl_one);
        mFLTwo = (FrameLayout)findViewById(R.id.fl_two);
        mFLThree = (FrameLayout)findViewById(R.id.fl_three);
        mFLFour = (FrameLayout)findViewById(R.id.fl_four);
        mFLFive = (FrameLayout)findViewById(R.id.fl_five);
        mFLSix = (FrameLayout)findViewById(R.id.fl_six);
        mFLSeven = (FrameLayout)findViewById(R.id.fl_seven);

        initData();
        initListener();
    }
    private void initData(){
        from = getIntent().getIntExtra("from",-1);
        if(from == FC_REPEAT){
            mTVTitle.setText("重复");
            mETCommon.setVisibility(View.GONE);
            mLLRepeat.setVisibility(View.VISIBLE);
        }else if(from == FC_TAG){
            mTVTitle.setText("标签");
            mLLRepeat.setVisibility(View.GONE);
            mETCommon.setVisibility(View.VISIBLE);
        }
    }
    private void initListener(){
        mFLBack.setOnClickListener(this);
        mFLOne.setOnClickListener(this);
        mFLTwo.setOnClickListener(this);
        mFLThree.setOnClickListener(this);
        mFLFour.setOnClickListener(this);
        mFLFive.setOnClickListener(this);
        mFLSix.setOnClickListener(this);
        mFLSeven.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == mFLBack){
            if(from == FC_REPEAT){
                doRepeatChoose();
                return;
            }
            if(from == FC_TAG){
                Intent data = new Intent();
                data.putExtra("tag",mETCommon.getText().toString()+"");
                setResult(RESULT_OK,data);
                this.finish();
            }
            return;
        }
        if(v == mFLOne){
            CheckBox cb =(CheckBox)mFLOne.getChildAt(1);
            cb.setChecked(!cb.isChecked());
            one = cb.isChecked();
            return;
        }
        if(v == mFLTwo){
            CheckBox cb =(CheckBox)mFLTwo.getChildAt(1);
            cb.setChecked(!cb.isChecked());
            two = cb.isChecked();
            return;
        }
        if(v == mFLThree){
            CheckBox cb =(CheckBox)mFLThree.getChildAt(1);
            cb.setChecked(!cb.isChecked());
            three = cb.isChecked();
            return;
        }
        if(v == mFLFour){
            CheckBox cb =(CheckBox)mFLFour.getChildAt(1);
            cb.setChecked(!cb.isChecked());
            four = cb.isChecked();
            return;
        }
        if(v == mFLFive){
            CheckBox cb =(CheckBox)mFLFive.getChildAt(1);
            cb.setChecked(!cb.isChecked());
            five = cb.isChecked();
            return;
        }
        if(v == mFLSix){
            CheckBox cb =(CheckBox)mFLSix.getChildAt(1);
            cb.setChecked(!cb.isChecked());
            six = cb.isChecked();
            return;
        }
        if(v == mFLSeven){
            CheckBox cb =(CheckBox)mFLSeven.getChildAt(1);
            cb.setChecked(!cb.isChecked());
            seven = cb.isChecked();
            return;
        }
    }

    private void doRepeatChoose(){
        Intent data = new Intent();
        data.putExtra("one",one);
        data.putExtra("two",two);
        data.putExtra("three",three);
        data.putExtra("four",four);
        data.putExtra("five",five);
        data.putExtra("six",six);
        data.putExtra("seven",seven);
        setResult(RESULT_OK,data);
        this.finish();
    }
}
