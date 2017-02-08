package com.winton.alarmclock;

import android.app.Application;

import com.winton.alarmclock.greendao.gen.DaoMaster;
import com.winton.alarmclock.greendao.gen.DaoSession;

import org.greenrobot.greendao.database.Database;

/**
 * Created by winton on 2017/2/8.
 */

public class MyApplication extends Application {
    private static DaoSession mDaoSession;
    private static MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static DaoSession getDaoSesion(){
        if(mDaoSession == null){
            DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(instance,"clock_db");
            Database database = helper.getWritableDb();
            mDaoSession = new DaoMaster(database).newSession();
        }
        return mDaoSession;
    }


}
