package fast.wq.com.fastandroid.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import fast.wq.com.fastandroid.bean.ListBean;

/**
 * Created by wangqiang on 2018/10/21.
 */

public class VipAdapterImpl extends VipAdapter<ListBean> {

    public VipAdapterImpl(Context context, List<ListBean> datas) {
        super(context, datas);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int layoutId = 1;
        VipViewHolder holder = VipViewHolder.getViewHolder(mContext, position, convertView, parent, layoutId);

        TextView title =  holder.getView(layoutId);
        return holder.getConvertView();
    }
}
