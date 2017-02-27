package com.administrator.zhiboviews;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //直播聊天室
        Button btn_1 = (Button) findViewById(R.id.btn_1);
        btn_1.setOnClickListener(this);
        //点赞动画
        Button btn_2 = (Button) findViewById(R.id.btn_2);
        btn_2.setOnClickListener(this);
        //弹出输入法
        Button btn_3 = (Button) findViewById(R.id.btn_3);
        btn_3.setOnClickListener(this);
        //上下拉刷新RecyclerView
        Button btn_4 = (Button) findViewById(R.id.btn_4);
        btn_4.setOnClickListener(this);
        //视频播放
        Button btn_5 = (Button) findViewById(R.id.btn_5);
        btn_5.setOnClickListener(this);

        //视频重启播放
        Button btn_6 = (Button) findViewById(R.id.btn_6);
        btn_6.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_1:
                startActivity(new Intent(this, LiveMessageActivity.class));
                break;

            case R.id.btn_2:
                startActivity(new Intent(this, DianZanActivity.class));
                Log.i("zuo","点赞动画");
                break;

            case R.id.btn_3:
                startActivity(new Intent(this, ShuRuActivity.class));
                Log.i("zuo","输入框");
                break;

            case R.id.btn_4:
                startActivity(new Intent(this, RefreshRecyclerViewActivity.class));
                Log.i("zuo","RecyclerViewActivity");
                break;

            case R.id.btn_5:
                startActivity(new Intent(this, VideoPlayerActivity.class));
                Log.i("zuo","VideoPlayerActivity");
                break;

            case R.id.btn_6:
                startActivity(new Intent(this, GuidVideoActivity.class));
                Log.i("zuo","GuidVideoActivity");
                break;
        }
    }
}
