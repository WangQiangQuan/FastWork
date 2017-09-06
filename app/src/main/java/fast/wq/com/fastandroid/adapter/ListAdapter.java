package fast.wq.com.fastandroid.adapter;

import android.content.Context;
import android.util.SparseIntArray;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import fast.wq.com.fastandroid.R;
import fast.wq.com.fastandroid.bean.ListBean;
import fast.wq.com.fastandroid.view.recyclerview.adapter.BaseQuickAdapter;
import fast.wq.com.fastandroid.view.recyclerview.adapter.BaseViewHolder;

/**
 * Created by admin on 2017/9/6.
 */

public class ListAdapter extends BaseQuickAdapter<ListBean> {

    public static final int TAB_DYNAMIC = 1001;
    public static final int TAB_STAR_USER = 1002;
    private Context mContext;
    private SparseIntArray layouts;


    public ListAdapter(Context context, List<ListBean> data) {
        super(data);

        this.mContext = context;
        addItemType(TAB_DYNAMIC, R.layout.item_row);
        addItemType(TAB_STAR_USER, R.layout.item_row);
    }


//    @Override
//    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        return super.onCreateViewHolder(parent, viewType);
//    }


    @Override
    protected int getDefItemViewType(int position) {
        int type = position % 2 == 0 ? TAB_DYNAMIC : TAB_STAR_USER;
        return type;
    }

    @Override
    protected BaseViewHolder onCreateDefViewHolder(ViewGroup parent, int viewType) {
        return createBaseViewHolder(parent, getLayoutId(viewType));
    }


    @Override
    protected void convert(BaseViewHolder holder, ListBean item) {
        int type = TAB_DYNAMIC;
        if (item.getType() == TAB_DYNAMIC) {
            type = TAB_DYNAMIC;
        } else if (item.getType() == TAB_STAR_USER) {
            type = TAB_STAR_USER;
        }
        switch (type) {
            case TAB_DYNAMIC:
                TextView tvTips = holder.getView(R.id.tv);
                tvTips.setText(item.getName());
                break;
            case TAB_STAR_USER:
                TextView action = holder.getView(R.id.action);
                action.setText(item.getName());
                break;
            default:
                break;
        }
    }

    private int getLayoutId(int viewType) {
        return layouts.get(viewType);
    }

    protected void addItemType(int type, int layoutResId) {
        if (layouts == null) {
            layouts = new SparseIntArray();
        }
        layouts.put(type, layoutResId);
    }
}
