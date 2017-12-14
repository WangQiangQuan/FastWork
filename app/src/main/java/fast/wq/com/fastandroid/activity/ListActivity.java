package fast.wq.com.fastandroid.activity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import fast.wq.com.fastandroid.R;
import fast.wq.com.fastandroid.adapter.ListAdapter;
import fast.wq.com.fastandroid.bean.ListBean;
import fast.wq.com.fastandroid.view.desin.CustomProgressDrawable;
import fast.wq.com.fastandroid.view.desin.CustomSwipeRefreshLayout;
import fast.wq.com.fastandroid.view.desin.DmSwipeRefreshLayout;
import fast.wq.com.fastandroid.view.recyclerview.decoration.DividerItemDecoration;

public class ListActivity extends Activity {

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private List<String> mDatas;
    private List<ListBean> mLists;
    private ListAdapter mAdapter;

    private CustomSwipeRefreshLayout mCustomSwipeRefreshLayout;
    private DmSwipeRefreshLayout mDmSwipeRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        initCustom();
//        initNotmal();

        initDm();
    }
    private void initDm(){
        setContentView(R.layout.activity_dm_list);
        initData();

        mDmSwipeRefreshLayout= findViewById(R.id.sr_refresh);
        mDmSwipeRefreshLayout.setColorSchemeResources(R.color.player_seekbar_progressb);
        mDmSwipeRefreshLayout.setOnRefreshListener(new DmSwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mDmSwipeRefreshLayout.setRefreshing(true);
//                mLists.clear();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(2000);
                            stopRefreshing();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });

        Log.d("wang", "initDm() called"+(mDmSwipeRefreshLayout == null));
        CustomProgressDrawable drawable = new CustomProgressDrawable(this,mDmSwipeRefreshLayout);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.dmloading);
        drawable.setBitmap(bitmap);
        mDmSwipeRefreshLayout.setProgressView(drawable);

        mRecyclerView = findViewById(R.id.listview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        mAdapter = new ListAdapter(this,mLists);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL_LIST));
    }
    private void initCustom(){
        setContentView(R.layout.activity_custm_list);
        initData();

        mCustomSwipeRefreshLayout= findViewById(R.id.sr_refresh);
        mCustomSwipeRefreshLayout.setColorSchemeResources(R.color.player_seekbar_progressb);
        mCustomSwipeRefreshLayout.setOnRefreshListener(new CustomSwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mCustomSwipeRefreshLayout.setRefreshing(true);
//                mLists.clear();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(2000);
                            stopRefreshing();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });

        CustomProgressDrawable drawable = new CustomProgressDrawable(this,mCustomSwipeRefreshLayout);
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.moments_refresh_icon);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.dmloading);
        drawable.setBitmap(bitmap);
        mCustomSwipeRefreshLayout.setProgressView(drawable);

        mRecyclerView = findViewById(R.id.listview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        mRecyclerView.setAdapter(new HomeAdapter());
//        mRecyclerView.setAdapter(new MulAdapter(this));

        mAdapter = new ListAdapter(this,mLists);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL_LIST));
    }
    private void initNotmal() {
        Log.i("wang", "onCreate: "+mLists.size());
        setContentView(R.layout.activity_list);
        initData();
        mSwipeRefreshLayout = findViewById(R.id.sr_refresh);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.player_seekbar_progressb);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeRefreshLayout.setRefreshing(true);
//                mLists.clear();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(2000);
                            stopRefreshing();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });

        mRecyclerView = findViewById(R.id.listview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        mRecyclerView.setAdapter(new HomeAdapter());
//        mRecyclerView.setAdapter(new MulAdapter(this));

        mAdapter = new ListAdapter(this,mLists);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL_LIST));
    }



    public void stopRefreshing() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                if (mSwipeRefreshLayout!= null)
                mSwipeRefreshLayout.setRefreshing(false);

                if (mCustomSwipeRefreshLayout!= null){
                    mCustomSwipeRefreshLayout.setRefreshing(false);
                }
                if (mDmSwipeRefreshLayout!=null){
                    mDmSwipeRefreshLayout.setRefreshing(false);
                }

            }
        });
    }

    protected void initData() {
        mDatas = new ArrayList<String>();
        mLists = new ArrayList<>();
        for (int i = 'A'; i < 'z'; i++) {
            mDatas.add("" + (char) i);

            ListBean bean = new ListBean();
            bean.setName("" + (char) i);
            if (i %2==0){
                bean.setType(ListAdapter.TAB_DYNAMIC);
            }else {
                bean.setType(ListAdapter.TAB_STAR_USER);
            }
            mLists.add(bean);
        }
    }

    /**
     * 两种通常的写法
     */

    class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {

        @Override
        public HomeAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                    ListActivity.this).inflate(R.layout.item_row, parent,
                    false));
            return holder;
        }

        @Override
        public void onBindViewHolder(HomeAdapter.MyViewHolder holder, int position) {
            holder.tv.setText(mDatas.get(position));
        }

        @Override
        public int getItemCount() {
            return mDatas.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            TextView tv;

            public MyViewHolder(View view) {
                super(view);
                tv = (TextView) view.findViewById(R.id.tv);
            }
        }
    }

    //建立枚举 2个item 类型
    public enum ITEM_TYPE {
        ITEM1,
        ITEM2
    }

    class MulAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private LayoutInflater mLayoutInflater;
        private Context context;


        public MulAdapter(Context context) {

            this.context = context;
            mLayoutInflater = LayoutInflater.from(context);
        }


        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            //加载Item View的时候根据不同TYPE加载不同的布局
            if (viewType == ITEM_TYPE.ITEM1.ordinal()) {
                return new Item1ViewHolder(mLayoutInflater.inflate(R.layout.item_row, parent, false));
            } else {
                return new Item2ViewHolder(mLayoutInflater.inflate(R.layout.item_row, parent, false));
            }

        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if (holder instanceof Item1ViewHolder) {
                ((Item1ViewHolder) holder).mTextView.setText(mDatas.get(position));
            } else if (holder instanceof Item2ViewHolder) {
                ((Item2ViewHolder) holder).mTextView.setText(mDatas.get(position));
            }
        }

        //设置ITEM类型，可以自由发挥，这里设置item position单数显示item1 偶数显示item2
        @Override
        public int getItemViewType(int position) {
            //Enum类提供了一个ordinal()方法，返回枚举类型的序数，这里ITEM_TYPE.ITEM1.ordinal()代表0， ITEM_TYPE.ITEM2.ordinal()代表1
            return position % 2 == 0 ? ITEM_TYPE.ITEM1.ordinal() : ITEM_TYPE.ITEM2.ordinal();
        }

        @Override
        public int getItemCount() {
            return mDatas.size();
        }

        //item1 的ViewHolder
        public class Item1ViewHolder extends RecyclerView.ViewHolder {
            TextView mTextView;

            public Item1ViewHolder(View itemView) {
                super(itemView);
                mTextView = (TextView) itemView.findViewById(R.id.tv);
            }
        }

        //item2 的ViewHolder
        public class Item2ViewHolder extends RecyclerView.ViewHolder {

            TextView mTextView;

            public Item2ViewHolder(View itemView) {
                super(itemView);
                mTextView = (TextView) itemView.findViewById(R.id.action);
            }
        }
    }

}
