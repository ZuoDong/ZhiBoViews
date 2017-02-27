package com.administrator.zhiboviews;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.administrator.zhiboviews.widget.LiveMessagePanel;

public class GuidVideoActivity extends AppCompatActivity
{  
    private SurfaceView surfaceView;
    private MediaPlayer mediaPlayer;
    private SurfaceHolder surfaceHolder;
    private boolean isSurfaceCreated =false;        //surface是否已经创建好  
    private Uri mUri;
    private int curIndex = 0;  
  
    @Override  
    protected void onCreate(Bundle savedInstanceState)
    {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.activity_log_reg);  
        initPlayerObj();  
        InitControl();  
    }  
  
    private void initPlayerObj()  
    {  
  
        mUri = Uri.parse("http://play.68mtv.com:8080/play7/2751.mp4");
        surfaceView = (SurfaceView) findViewById(R.id.sv);  
  
        mediaPlayer = new MediaPlayer();  
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);      //设置视频流类型
  
        CreateSurface();  
    }  
  
    private void InitControl()  
    {
        Button LoginRegLayout = (Button)findViewById(R.id.log_reg_btn);
        Button LookRoundLayout = (Button)findViewById(R.id.look_round_btn);
        Button SkipImg = (Button)findViewById(R.id.skip_img);                //右上角跳过文本
  
        ClickEventDelegate ClickListener = new ClickEventDelegate();  
        LoginRegLayout.setOnClickListener(ClickListener);  
        LookRoundLayout.setOnClickListener(ClickListener);  
  
        SkipImg.setOnClickListener(ClickListener);  
  
    }  
  
    private class ClickEventDelegate implements View.OnClickListener
    {  
        @Override  
        public void onClick(View v)  
        {  
            switch(v.getId())  
            {  
                case R.id.log_reg_btn:
                    startActivity(new Intent(GuidVideoActivity.this,LiveMessageActivity.class));
                    break;  
  
                case R.id.look_round_btn:  
                    mediaPlayer.start();
                    break;  
  
                case R.id.skip_img:  
                    mediaPlayer.pause();
                    break;  
            }  
        }  
    }  
  
    /** 
     * 创建视频展示页面 
     */  
    private void CreateSurface()  
    {  
        surfaceHolder = surfaceView.getHolder();  
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS); //兼容4.0以下的版本  
        surfaceHolder.addCallback(new SurfaceHolder.Callback() {  
  
            @Override  
            public void surfaceDestroyed(SurfaceHolder holder) {  
  
                isSurfaceCreated = false;  
                if(mediaPlayer.isPlaying())//此处需要注意
                {  
                    curIndex = mediaPlayer.getCurrentPosition();  
                    mediaPlayer.stop();  
                }  
            }  
  
            @Override  
            public void surfaceCreated(SurfaceHolder holder) {  
                isSurfaceCreated = true;  
                mediaPlayer.setDisplay(surfaceHolder);//页面创建好了以后再展示
            }  
  
            @Override  
            public void surfaceChanged(SurfaceHolder holder, int format,  
                                       int width, int height) {  
  
            }  
        });  
  
    }  
  
    /** 
     * 释放播放器资源 
     */  
    private void ReleasePlayer()  
    {  
        if(mediaPlayer!=null)  
        {  
            mediaPlayer.stop();  
            mediaPlayer.release();  
            mediaPlayer = null;  
        }  
  
    }  
  
    /** 
     * 暂停 
     */  
    private void Pause()  
    {  
        if (mediaPlayer != null && mediaPlayer.isPlaying())  
        {  
            curIndex = mediaPlayer.getCurrentPosition();  
            mediaPlayer.pause();
        }  
  
    }  
  
  
    private void Play(final int currentPosition,Uri mUri)  
    {  
        try  
        {  
            mediaPlayer.reset();  
            mediaPlayer.setDataSource(GuidVideoActivity.this,mUri);
            mediaPlayer.setDisplay(surfaceView.getHolder());  
            mediaPlayer.setLooping(true);  
            mediaPlayer.prepareAsync();  
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener()  
            {  
  
                @Override  
                public void onPrepared(MediaPlayer mp)  
                {  
                    mediaPlayer.seekTo(currentPosition);  
                    mediaPlayer.start();  
                }  
            });  
  
            mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {  
  
                @Override  
                public boolean onError(MediaPlayer mp, int what, int extra) {  
  
                    return false;  
                }  
            });  
        }  
        catch (Exception e)  
        {  
            Log.i("zuo","line:210--GuidVideoActivity--Play--error");
        }  
    }  
  
    /** 
     * 创建完毕页面后需要将播放操作延迟10ms防止因surface创建不及时导致播放失败 
     */  
    @Override  
    protected void onResume()  
    {  
        super.onResume();  
        new Handler().postDelayed(new Runnable()
        {  
            public void run()  
            {  
                if(isSurfaceCreated)  
                {  
                    Play(curIndex,mUri);  
                }  
            }  
        }, 10);  
  
    }  
  
    /** 
     * 页面从前台到后台会执行 onPause ->onStop 此时Surface会被销毁， 
     * 再一次从后台 到前台时需要 重新创建Surface 
     */  
    @Override  
    protected void onRestart()  
    {  
        super.onRestart();  
        if(!isSurfaceCreated)  
        {  
            CreateSurface();  
        }  
    }  
  
  
    @Override  
    protected void onPause()  
    {  
        super.onPause();  
        Pause();  
    }  
  
    @Override  
    protected void onStop()  
    {  
        super.onStop();
    }  
  
    @Override  
    protected void onDestroy()  
    {  
        super.onDestroy();  
        ReleasePlayer();
    }  
  
    @Override  
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {  
        if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN)  
        {  
            finish();  
            return true;  
        }  
        return super.onKeyDown(keyCode, event);  
    }  
}  