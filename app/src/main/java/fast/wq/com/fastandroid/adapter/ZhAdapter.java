package fast.wq.com.fastandroid.adapter;

import java.util.List;

import fast.wq.com.fastandroid.bean.ListBean;
import fast.wq.com.fastandroid.view.recyclerview.adapter.BaseQuickAdapter;
import fast.wq.com.fastandroid.view.recyclerview.adapter.BaseViewHolder;

/**
 * Created by admin on 2018/2/8.
 */

public class ZhAdapter extends BaseQuickAdapter<ListBean> {
    public ZhAdapter(int layoutResId, List<ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder holder, ListBean item) {

    }
}
