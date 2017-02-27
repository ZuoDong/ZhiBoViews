package com.administrator.zhiboviews;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import cn.bingoogolapple.refreshlayout.BGARefreshViewHolder;

public class RefreshRecyclerViewActivity extends AppCompatActivity implements BGARefreshLayout.BGARefreshLayoutDelegate{

    private List<String> mList = new ArrayList<>();
    private BGARefreshLayout mRefreshLayout;
    private RecyclerView recycle_view;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refresh_recycler_view);

        initRefreshLayout();
    }

    private void initRefreshLayout() {
        mRefreshLayout = (BGARefreshLayout) findViewById(R.id.bga_refresh_layout_view);
        recycle_view = (RecyclerView) findViewById(R.id.recycle_view);
        recycle_view.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        // 为BGARefreshLayout设置代理
        mRefreshLayout.setDelegate(this);
        // 设置下拉刷新和上拉加载更多的风格     参数1：应用程序上下文，参数2：是否具有上拉加载更多功能
        BGARefreshViewHolder refreshViewHolder = new BGANormalRefreshViewHolder(this, true);
        // 设置下拉刷新和上拉加载更多的风格
        mRefreshLayout.setRefreshViewHolder(refreshViewHolder);

        mList.addAll(getListData("我是本来的数据"));
        adapter = new MyAdapter(this, mList);
        recycle_view.setAdapter(adapter);


        // 为了增加下拉刷新头部和加载更多的通用性，提供了以下可选配置选项  -------------START
        // 设置正在加载更多时不显示加载更多控件
        // mRefreshLayout.setIsShowLoadingMoreView(false);
        // 设置正在加载更多时的文本
//        refreshViewHolder.setLoadingMoreText("加载中...");
        // 设置整个加载更多控件的背景颜色资源id
//        refreshViewHolder.setLoadMoreBackgroundColorRes(android.R.color.white);
        // 设置整个加载更多控件的背景drawable资源id
//        refreshViewHolder.setLoadMoreBackgroundDrawableRes(loadMoreBackgroundDrawableRes);
        // 设置下拉刷新控件的背景颜色资源id
//        refreshViewHolder.setRefreshViewBackgroundColorRes(refreshViewBackgroundColorRes);
        // 设置下拉刷新控件的背景drawable资源id
//        refreshViewHolder.setRefreshViewBackgroundDrawableRes(refreshViewBackgroundDrawableRes);
        // 设置自定义头部视图（也可以不用设置）     参数1：自定义头部视图（例如广告位）， 参数2：上拉加载更多是否可用
//        mRefreshLayout.setCustomHeaderView(mBanner, false);
        // 可选配置  -------------END
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        // 在这里加载最新数据

        if (true) {
            // 如果网络可用，则加载网络数据
            new AsyncTask<Void, Void, Void>() {

                @Override
                protected Void doInBackground(Void... params) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return null;
                }

                @Override
                protected void onPostExecute(Void aVoid) {
                    // 加载完毕后在UI线程结束下拉刷新
                    mRefreshLayout.endRefreshing();
                    adapter.updateData(getListData("我是新的数据"));
                }
            }.execute();
        } else {
            // 网络不可用，结束下拉刷新
            Toast.makeText(this, "网络不可用", Toast.LENGTH_SHORT).show();
            mRefreshLayout.endRefreshing();
        }
    }

    //模拟假数据
    private List<String> getListData(String str) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(str+i);
        }
        return list;
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        // 在这里加载更多数据，或者更具产品需求实现上拉刷新也可以
        if (true) {
            // 如果网络可用，则异步加载网络数据，并返回true，显示正在加载更多
            new AsyncTask<Void, Void, Void>() {

                @Override
                protected Void doInBackground(Void... params) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return null;
                }

                @Override
                protected void onPostExecute(Void aVoid) {
                    // 加载完毕后在UI线程结束加载更多
                    mRefreshLayout.endLoadingMore();
                    adapter.addDatas(getListData("我是更多的数据"));
                }
            }.execute();

            return true;
        } else {
            // 网络不可用，返回false，不显示正在加载更多
            Toast.makeText(this, "网络不可用", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    // 通过代码方式控制进入正在刷新状态。应用场景：某些应用在activity的onStart方法中调用，自动进入正在刷新状态获取最新数据
    public void beginRefreshing() {
        mRefreshLayout.beginRefreshing();
    }

    // 通过代码方式控制进入加载更多状态
    public void beginLoadingMore() {
        mRefreshLayout.beginLoadingMore();
    }


    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{


        private  Context context;
        private  List<String> list = new ArrayList<>();

        public MyAdapter(Context context, List<String> list) {
            this.context = context;
            this.list = list;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = View.inflate(context, R.layout.item_recycler_view, parent);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.viewById.setText(list.get(position));
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public void updateData(List<String> datas) {
            if(list != null){
                list.clear();
            }
            list.addAll(datas);
            notifyDataSetChanged();
        }

        public void addDatas(List<String> datas) {
            list.addAll(datas);
            notifyDataSetChanged();
        }

        public class ViewHolder extends RecyclerView.ViewHolder{

            public TextView viewById;

            public ViewHolder(View view) {
                super(view);
                viewById = (TextView) view.findViewById(R.id.content);
            }
        }
    }
}
