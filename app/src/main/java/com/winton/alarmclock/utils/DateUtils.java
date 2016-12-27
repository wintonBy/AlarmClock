package com.winton.alarmclock.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by winton on 2016/12/9.
 */

public final class DateUtils {

    public static String longDateToTime(long time){
        Date date = new Date(time);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        return simpleDateFormat.format(date);
    }

    public static boolean isNight(String strTime){
        String tmpArray[] = strTime.split(":");
        String hour = tmpArray[0];
        int intHour = Integer.parseInt(hour);
        if(intHour > 6 && intHour <18){
            return false;
        }else {
            return true;
        }

    }

}
