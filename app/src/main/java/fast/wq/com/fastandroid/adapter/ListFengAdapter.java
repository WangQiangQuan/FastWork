package fast.wq.com.fastandroid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import fast.wq.com.fastandroid.R;
import fast.wq.com.fastandroid.view.recyclerview.fengzhuang.DmRecommend;
import fast.wq.com.fastandroid.view.recyclerview.fengzhuang.base.DmBaseAdapter;
import fast.wq.com.fastandroid.view.recyclerview.fengzhuang.base.DmBaseViewHolder;

/**
 * Created by admin on 2017/10/30.
 */

public class ListFengAdapter extends DmBaseAdapter<DmRecommend> {
    private LayoutInflater mInflater;

    public ListFengAdapter(Context context) {
        super(context);
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public DmBaseViewHolder<DmRecommend> onCreateAdapterViewHolder(ViewGroup parent, int viewType) {
        return new TitleViewHolder(mInflater.inflate(R.layout.item_row, parent, false));
    }

    @Override
    public void onBindAdapterViewHolder(DmBaseViewHolder<DmRecommend> holder, int dataListPosition) {
        DmRecommend recommend = getAdapterDataItem(dataListPosition);
        holder.updateData(recommend, dataListPosition);
    }

    public class TitleViewHolder extends DmBaseViewHolder<DmRecommend> {
        TextView tvTips;

        public TitleViewHolder(View itemView) {
            super(itemView);
            tvTips = itemView.findViewById(R.id.tv);
        }

        @Override
        public void updateData(DmRecommend dmRecommend, int position) {
            super.updateData(dmRecommend, position);

            tvTips.setText(dmRecommend.name);
        }
    }

    public void addData(List<DmRecommend> dataList){
        mDataList.addAll(dataList);
        notifyDataSetChanged();
    }
}
