package fast.wq.com.fastandroid.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import fast.wq.com.fastandroid.R;
import fast.wq.com.fastandroid.adapter.ListFengAdapter;
import fast.wq.com.fastandroid.view.recyclerview.fengzhuang.DmRecommend;
import fast.wq.com.fastandroid.view.recyclerview.fengzhuang.base.DmRecyclerViewWrapper;

public class ListFengActivity extends Activity {
    private static final String TAG = "ListFengActivity";
    DmRecyclerViewWrapper recyclerViewWrapper;
    ListFengAdapter mAdapter;
    private LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_feng);
        initData();
        recyclerViewWrapper = findViewById(R.id.content_lv);
        mAdapter = new ListFengAdapter(this);
        recyclerViewWrapper.setAdapter(mAdapter);

        layoutManager = new LinearLayoutManager(this);
        recyclerViewWrapper.setLayoutManager(layoutManager);
        recyclerViewWrapper.setOnLoadMoreListener(new DmRecyclerViewWrapper.OnLoadMoreListener() {
            @Override
            public void loadMore(int itemsCount, int maxLastVisiblePosition) {
                Log.d(TAG, "loadMore() called with: itemsCount = [" + itemsCount + "], maxLastVisiblePosition = [" + maxLastVisiblePosition + "]");
                recyclerViewWrapper.enableLoadMore(false);
            }
        });
        recyclerViewWrapper.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.d(TAG, "onRefresh() called");
                recyclerViewWrapper.enableRefresh(false);
                recyclerViewWrapper.enableRefresh(true);
            }
        });
        recyclerViewWrapper.enableLoadMore(true);
        recyclerViewWrapper.enableRefresh(true);
        mAdapter.addData(mLists);
    }

    private List<String> mDatas;
    private List<DmRecommend> mLists;
    protected void initData() {
        mDatas = new ArrayList<String>();
        mLists = new ArrayList<>();
        for (int i = 'A'; i < 'z'; i++) {
            mDatas.add("" + (char) i);

            DmRecommend bean = new DmRecommend();
            bean.name = ("" + (char) i);
            mLists.add(bean);
        }
    }
}
