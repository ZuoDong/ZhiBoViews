package com.administrator.zhiboviews;

import android.app.Application;
import android.content.Context;

/**
 * Created by Administrator on 2017/3/8.
 */

public class MyApplication extends Application {

    public static Context CONTEXT;

    @Override
    public void onCreate() {
        super.onCreate();
        CONTEXT = getApplicationContext();
    }
}
