package com.administrator.zhiboviews.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.WindowManager;

import com.administrator.zhiboviews.MyApplication;
import com.administrator.zhiboviews.dialog.ApplicationDialog;

/**
 * Created by Administrator on 2017/3/8.
 */

public class DialogService extends Service {

    private ApplicationDialog dialog;

    private Handler handler = new Handler();

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("zuo","service创建了");
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.i("zuo","弹框执行");
                final ApplicationDialog dialog = new ApplicationDialog(MyApplication.CONTEXT);
                dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_APPLICATION_ATTACHED_DIALOG);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        dialog.show();
                    }
                });
            }
        }).start();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("zuo","service销毁了");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
