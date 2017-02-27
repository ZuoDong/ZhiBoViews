package com.administrator.zhiboviews;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import com.administrator.zhiboviews.widget.PeriscopeLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DianZanActivity extends AppCompatActivity {

    @BindView(R.id.pl_dian_zan)
    PeriscopeLayout plDianZan;
    @BindView(R.id.activity_dian_zan)
    RelativeLayout activityDianZan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dian_zan);
        ButterKnife.bind(this);
        Log.i("zuo","DianZanActivity-->onCreat()");
    }

}
