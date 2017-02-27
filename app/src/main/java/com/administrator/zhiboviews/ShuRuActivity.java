package com.administrator.zhiboviews;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;

import com.administrator.zhiboviews.dialog.InputMsgDialog;

import java.util.Timer;
import java.util.TimerTask;


public class ShuRuActivity extends AppCompatActivity {


//    @BindView(R.id.input_msg)
//    EditText inputMsg;
//    @BindView(R.id.activity_shu_ru)
//    ResizeLayout activityShuRu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_shu_ru);
//        ButterKnife.bind(this);

        Log.i("zuo", "创建完成");
        InputMsgDialog fragmnet = new InputMsgDialog(this);
        fragmnet.show();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }, 500);  //在一秒后打开
    }

}
