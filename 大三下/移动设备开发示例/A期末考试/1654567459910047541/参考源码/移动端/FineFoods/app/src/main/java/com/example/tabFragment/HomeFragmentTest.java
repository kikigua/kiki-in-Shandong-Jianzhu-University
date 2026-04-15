package com.example.tabFragment;

import java.util.List;
import com.example.MyGridView.MyGridView;
import com.example.activity.R;
import com.example.downLoader.ImageDownLoader;
import com.example.util.MyToast;
import com.example.util.NetInfoUtil;
import com.example.util.Utils;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.example.pull_to_refresh.PullToRefreshView;
import com.example.pull_to_refresh.PullToRefreshView.OnHeaderRefreshListener;

public class HomeFragmentTest extends Fragment implements OnClickListener,
        OnHeaderRefreshListener {
    String timediver = "now()"; // 查询时间点
    private Handler mHandler; // //handlrer更新
    final int DISCON = 0;	//断网
    final int CLOCK = 4; // 定时器
    final int REFRESH = 5;	//刷新
    List<String[]> menuInfo; // 菜谱全部信息
    ImageDownLoader imageLoader;
    private TextView menu_add;
    private MyGridView menu_ll;
    private PullToRefreshView mPullToRefreshView;
    private FrameLayout fl;
    private ProgressBar pb;
    private LinearLayout again_ll;
    MyToast toast;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case REFRESH:		//刷新，每刷新一次，都重新加载一下最新信息
                        pb.setVisibility(View.VISIBLE);
                        again_ll.setVisibility(View.GONE);
                        mPullToRefreshView.onHeaderRefreshComplete();
                        initMenu();    //在界面上加载全部菜谱
                        fl.setVisibility(View.GONE);
                        break;
                    case DISCON:
                        toast.showToast("网络已中断,请联网后重试");
                        fl.setVisibility(View.VISIBLE);
                        pb.setVisibility(View.GONE);
                        again_ll.setVisibility(View.VISIBLE);
                        break;
                }
            }
        };

        new Thread() {
            public void run() {
                while (true) {
                    try {
                        sleep(2000);
                        mHandler.sendEmptyMessage(CLOCK);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FrameLayout  home_ll=(FrameLayout) inflater.inflate(R.layout.hometest,null);
        toast=new MyToast(getActivity());
        imageLoader = new ImageDownLoader();
        mPullToRefreshView = (PullToRefreshView)home_ll.findViewById(R.id.main_pull_refresh_view);
        mPullToRefreshView.lockFooter();	//锁住上拉
        mPullToRefreshView.lockHead();
        //获取全部菜单到menu_ll
        menu_ll = (MyGridView) mPullToRefreshView.findViewById(R.id.menu_ll);
        mPullToRefreshView = (PullToRefreshView) mPullToRefreshView.findViewById(R.id.main_pull_refresh_view);
        mPullToRefreshView.setOnHeaderRefreshListener(this);
        again_ll=(LinearLayout) home_ll.findViewById(R.id.again_ll);
        pb=(ProgressBar) home_ll.findViewById(R.id.pb);
        fl=(FrameLayout) home_ll.findViewById(R.id.home_nonet);
        Button again=(Button) home_ll.findViewById(R.id.nonet_again);
        again.setOnClickListener(
                new OnClickListener()
                {
                    @Override
                    public void onClick(View v) {
                        if (Utils.isNewWork(getActivity())) {
                            new Refresh().start();
                        }else
                        {
                            mHandler.sendEmptyMessage(DISCON);
                        }
                    }
                });
        again.performClick();
        return home_ll;
    }

    // /刷新线程
    private class Refresh extends Thread {
        @Override
        public void run() {
            try {
                timediver = "now()";
                menuInfo = NetInfoUtil.getAllMenu();     //从数据库中获取全部菜品
                mHandler.sendEmptyMessage(REFRESH);  //发送“刷新”消息，激活hanlder继续刷新
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    //在界面上加载全部菜谱
    private void initMenu()
    {
        BaseAdapter ba=new BaseAdapter()
        {
            @Override
            public int getCount() {
                // TODO Auto-generated method stub
                return menuInfo.size();
            }
            @Override
            public Object getItem(int position) {
                // TODO Auto-generated method stub
                return null;
            }

            @Override
            public long getItemId(int position) {
                // TODO Auto-generated method stub
                return 0;
            }
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                String[] strs=menuInfo.get(position);
                String name=strs[1];
                //primaryPic为菜品图片名
                String primaryPic=strs[2];
                String food=strs[3];
                //先获取LayoutInflater对象（from方法），再装载列表项布局到布局变量menu_item
                //inflate方法 是用来找 res/layout 下的 xml 布局文件，并且实例化
                //findViewById方法是找具体 xml 布局文件中的具体 widget 控件(如:Button、TextView 等)。
                LinearLayout menu_item=(LinearLayout) LayoutInflater.from(getActivity()).inflate(R.layout.hometest_menu_item, null);
                int width=Utils.getScreenWidth(getActivity())*7/30;   //根据屏幕大小计算每张图片的高和宽（两者相等）
                //LinearLayout.LayoutParams用于动态设置控件的布局参数
                //其第一个参数为宽的设置，第二个参数为高的设置。
                LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(width, width);
                ImageView iv=(ImageView) menu_item.findViewById(R.id.image);
                iv.setLayoutParams(params);
                //从缓存或SD卡或服务器获取图片并设置到图片控件iv上
                imageLoader.thumbnailExcute(iv, primaryPic);
                TextView cname=(TextView) menu_item.findViewById(R.id.name);
                cname.setText(name);
                TextView tvfood=(TextView) menu_item.findViewById(R.id.food);
                tvfood.setText("菜品主料："+food);
                return menu_item;
            }
        };
        menu_ll.setAdapter(ba);
        menu_ll.setSelector(new ColorDrawable(Color.TRANSPARENT));
    }
    @Override
    public void onClick(View v) {
    }
    @Override
    public void onHeaderRefresh(PullToRefreshView view) {
        mPullToRefreshView.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (Utils.isNewWork(getActivity())) {
                    new Refresh().start();
                }else
                {
                    mHandler.sendEmptyMessage(DISCON);
                }
            }
        }, 1000);
    }
    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        imageLoader.cancelTask();
    }
}
