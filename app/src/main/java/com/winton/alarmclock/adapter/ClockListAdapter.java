package com.winton.alarmclock.adapter;

import android.content.Context;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.winton.alarmclock.R;
import com.winton.alarmclock.entity.Clock;
import com.winton.alarmclock.utils.DateUtils;

import java.util.List;

/**
 * Created by winton on 2016/12/8.
 */

public class ClockListAdapter extends BaseAdapter<Clock> {

    private LayoutInflater inflater ;

    public ClockListAdapter(Context mContext, List<Clock> sourceList) {
        super(mContext, sourceList);
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Clock item = sourceList.get(i);

        ViewHolder holder;
        if(view == null){
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.item_clock_view,viewGroup,false);
            holder.tvClockTime = (TextView)view.findViewById(R.id.tv_clock_time);
            holder.tvDesc = (TextView)view.findViewById(R.id.tv_clock_word);
            holder.ivClockIcon = (ImageView)view.findViewById(R.id.iv_clock);
            holder.swClock = (SwitchCompat)view.findViewById(R.id.sw_clock);
            holder.llDays = (LinearLayout)view.findViewById(R.id.ll_days);
            view.setTag(holder);
        }else {
            holder = (ViewHolder)view.getTag();
        }
        holder.tvClockTime.setText(item.getHour()+":"+item.getMinute());
        ((CheckBox)holder.llDays.getChildAt(0)).setChecked(item.getSun());
        ((CheckBox)holder.llDays.getChildAt(1)).setChecked(item.getMon());
        ((CheckBox)holder.llDays.getChildAt(2)).setChecked(item.getTues());
        ((CheckBox)holder.llDays.getChildAt(3)).setChecked(item.getWed());
        ((CheckBox)holder.llDays.getChildAt(4)).setChecked(item.getThurs());
        ((CheckBox)holder.llDays.getChildAt(5)).setChecked(item.getFri());
        ((CheckBox)holder.llDays.getChildAt(6)).setChecked(item.getSat());
        holder.tvDesc.setText(item.getTitle());
        String cTime = holder.tvClockTime.getText().toString();
        boolean isNight = DateUtils.isNight(cTime);
        holder.ivClockIcon.setImageDrawable(isNight ? mContext.getResources().getDrawable(R.mipmap.ic_moon):mContext.getResources().getDrawable(R.mipmap.ic_sun));
        view.setBackgroundColor(isNight?mContext.getResources().getColor(R.color.colorItemBgNight) : mContext.getResources().getColor(R.color.colorItemBgDay));
        return view;
    }

    class ViewHolder {
        TextView tvClockTime;
        TextView tvDesc;
        ImageView ivClockIcon;
        SwitchCompat swClock;
        LinearLayout llDays;

    }
}
