package com.administrator.zhiboviews;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AnimatiorActivity extends AppCompatActivity implements View.OnClickListener{

    TextView attentation;
    RelativeLayout zhuBoName;
    private long startTime;
    private long endTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animatior);
        ButterKnife.bind(this);

        attentation = (TextView) findViewById(R.id.attentation);
        zhuBoName = (RelativeLayout) findViewById(R.id.zhu_bo_name);
        attentation.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.attentation:
                startAnimation();
                break;
        }
    }

    public void startAnimation(){
        int shortValue = zhuBoName.getWidth() - attentation.getWidth();
        int longValue = zhuBoName.getWidth();
        attentation.setVisibility(View.GONE);
        final int left = zhuBoName.getLeft();
        final int top = zhuBoName.getTop();
        final int right = zhuBoName.getRight();
        final int bottom = zhuBoName.getBottom();
        ValueAnimator animator = ValueAnimator.ofInt(longValue, shortValue);
        animator.setDuration(500);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                zhuBoName.layout(left,top,value,bottom);
            }
        });
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                startTime = System.currentTimeMillis();
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                endTime = System.currentTimeMillis();
                Log.i("value","动画时间为：" + (endTime - startTime) + "ms");
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animator.start();
    }
}
