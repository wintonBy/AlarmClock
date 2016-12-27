package com.winton.alarmclock.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by winton on 2016/12/8.
 */

public abstract class BaseAdapter<T> extends android.widget.BaseAdapter {

    public Context mContext;
    public List<T> sourceList;

    public BaseAdapter(Context mContext, List<T> sourceList) {
        this.mContext = mContext;
        this.sourceList = sourceList;
    }

    @Override
    public int getCount() {
        if(sourceList != null){
            return sourceList.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int i) {
        if(sourceList != null){
            return sourceList.get(i);
        }
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public abstract View getView(int i, View view, ViewGroup viewGroup) ;
}
