package com.administrator.zhiboviews;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.administrator.zhiboviews.bean.MessageBean;
import com.administrator.zhiboviews.widget.LiveMessagePanel;

/**
 * Created by zuo on 2016/10/17.
 */
public class LiveMessageActivity extends AppCompatActivity{

    private LiveMessagePanel message_panel;
    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.livemessage_activity);

        message_panel = (LiveMessagePanel) findViewById(R.id.message_panel);
        Button btn_send_msg = (Button) findViewById(R.id.btn_send_msg);
        btn_send_msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MessageBean messageBean = new MessageBean();
                messageBean.setName("zuo");
                messageBean.setContent("helloWord"+count);
                message_panel.addMessage(messageBean);
                count++;
            }
        });
    }


}
