package fast.wq.com.fastandroid.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 *
 */
public class VipViewHolder {

    private SparseArray<View> mViews;
    private int mPostion;
    private View mConvertView;

    public VipViewHolder(Context context, ViewGroup parent, int layoutId, int postion) {

        this.mViews = new SparseArray<>();
        this.mPostion = postion;
        this.mConvertView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        this.mConvertView.setTag(this);
    }

    public static VipViewHolder getViewHolder(Context context, int position, View convertView, ViewGroup parent, int layoutId) {

        if (convertView == null) {
            return new VipViewHolder(context, parent, layoutId, position);
        } else {
            VipViewHolder holder = (VipViewHolder) convertView.getTag();
            holder.mPostion = position;
            return holder;
        }
    }


    public View getConvertView() {
        return mConvertView;
    }

    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }
}
/**
 *   @Override
public View getView(int position, View convertView, ViewGroup parent) {

int layoutId = 1;
VipViewHolder holder = VipViewHolder.getViewHolder(mContext, position, convertView, parent, layoutId);

TextView title =  holder.getView(layoutId);
return holder.getConvertView();
}
 */
