package com.administrator.zhiboviews.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.administrator.zhiboviews.R;
import com.administrator.zhiboviews.bean.MessageBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zuo on 2016/10/17.
 */

public class LiveMessagePanel extends LinearLayout implements View.OnClickListener{

    private ListView lv_message_panel;
    private Button btn_scroll_to_bottom_msg;
    private List<MessageBean> messageslist = new ArrayList<>();
    private MessagePanelAdapter adapter;
    private int beforePosition = 0;

    public LiveMessagePanel(Context context) {
        this(context,null);
    }

    public LiveMessagePanel(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public LiveMessagePanel(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        View msgPanel = View.inflate(getContext(), R.layout.messagepanel,null);
        lv_message_panel = (ListView) msgPanel.findViewById(R.id.lv_message_panel);
        btn_scroll_to_bottom_msg = (Button) msgPanel.findViewById(R.id.btn_scroll_to_bottom_msg);
        btn_scroll_to_bottom_msg.setOnClickListener(this);
        adapter = new MessagePanelAdapter(getContext(), messageslist);
        lv_message_panel.setAdapter(adapter);
        //添加聊天布局
        addView(msgPanel,new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
        //监听向上滑动
        lv_message_panel.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if(firstVisibleItem < beforePosition){
                    //更改滑动模式
                    lv_message_panel.setTranscriptMode(AbsListView.TRANSCRIPT_MODE_NORMAL);
                }else{
                    beforePosition = firstVisibleItem;
                }
            }
        });
    }

    public void addMessage(MessageBean messageBean){
        adapter.addMessage(messageBean);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_scroll_to_bottom_msg:
                lv_message_panel.setSelection(lv_message_panel.getBottom());
                lv_message_panel.setTranscriptMode(AbsListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
                break;
        }
    }


    class MessagePanelAdapter extends BaseAdapter{

        private Context context;
        private List<MessageBean> messageslist = new ArrayList<>();

        public MessagePanelAdapter(Context context, List<MessageBean> messageslist) {
            this.context = context;
            this.messageslist = messageslist;
        }

        @Override
        public int getCount() {
            return messageslist.size();
        }

        @Override
        public Object getItem(int position) {
            return messageslist.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        public void addMessage(MessageBean messageBean){
            messageslist.add(messageBean);
            notifyDataSetChanged();
        }

        public void addMessageList(List<MessageBean> messageslist){
            this.messageslist = messageslist;
        }

        private ViewHolder viewHolder;

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            if(convertView == null){
                viewHolder = new ViewHolder();
                convertView = View.inflate(context, R.layout.item_message, null);
                viewHolder.btn_user_name = (TextView) convertView.findViewById(R.id.btn_user_name);
                viewHolder.iv_user_rank = (ImageView) convertView.findViewById(R.id.iv_user_rank);
                viewHolder.tv_content = (TextView) convertView.findViewById(R.id.tv_content);
                convertView.setTag(viewHolder);
            }else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            MessageBean messageBean = messageslist.get(position);
            viewHolder.btn_user_name.setText(messageBean.getName());
            viewHolder.btn_user_name.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(),"当前位置为"+position,Toast.LENGTH_SHORT).show();
                }
            });
            viewHolder.tv_content.setText(messageBean.getContent());
            if(messageBean.getRank() == 2){
                viewHolder.iv_user_rank.setVisibility(View.VISIBLE);
            }
            return convertView;
        }

        class ViewHolder {
            TextView btn_user_name;
            ImageView iv_user_rank;
            TextView tv_content;
        }
    }

}
