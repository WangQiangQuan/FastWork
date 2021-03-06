package fast.wq.com.fastandroid.view.recyclerview.fengzhuang.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *  wrapp RecyclerView.Adapter<br/>
 *  1. can add/remove multiple headerView with/without animation<br/>
 *  2. instead of using Adapter.notifyItem/Range changed , using notifyAdapterItem/Range chagned<br/>
 *  3. dataList Position is different with adapter position, adapter position contains header view<br/>
 *  4. constraint : your value type should be between : 0 ~ Integer.MAX_VALUE-100<br/>
 * @param <T>
 * @author yqpan
 * @date 2016/1/13.
 */
public abstract class DmBaseAdapter<T> extends RecyclerView.Adapter<DmBaseViewHolder<T>> {
    //HEADER view type Integer.MIN_VALUE ~ mMaxHeaderType
    //FOOTER view type mMinFooterType ~ Integer.MAX_VALUE
    protected static final int VIEW_TYPE_FOOTER = -1;
    protected static final int VIEW_TYPE_FOOTER_VIEW = -2;
    protected static final int VIEW_TYPE_NORMAL = 0;

    protected Context mContext;
    protected LayoutInflater mInflater;
    protected List<T> mDataList = new ArrayList<>();

    private View mCustomLoadingView;
    private LinearLayout footerViewLayout;

    private boolean mEnableLoadingMore;

    private List<View> mHeadViews;
    private SparseArray<View> mTypeViewMap;
    private Map<View,Integer> mViewTypeMap;

    private int mMinHeaderType;
    private int mMaxHeaderType;
    private int mLastHeaderType;

    private int mLastFooterType;
    private int mMinFooterType;
    private int mMaxFooterType;


    public DmBaseAdapter(Context context) {
        mContext = context.getApplicationContext();
        mInflater = LayoutInflater.from(mContext);
        mHeadViews = new ArrayList<>();
        mTypeViewMap = new SparseArray<>();
        mViewTypeMap = new HashMap<>();

        mMinHeaderType = Integer.MIN_VALUE;
        mMaxHeaderType = mMinHeaderType + 100;
        mLastHeaderType = mMinHeaderType;

        mMaxFooterType = Integer.MAX_VALUE;
        mMinFooterType = mMaxFooterType - 100;
        mLastFooterType = mMinFooterType;
        footerViewLayout = new LinearLayout(context);
        footerViewLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT , ViewGroup.LayoutParams.WRAP_CONTENT));
        footerViewLayout.setOrientation(LinearLayout.VERTICAL);
    }

    public final void addHeaderView(View view){
        addHeaderView(view, false);
    }

    public final void addHeaderView(View view , boolean hasAnimation){
        if(view == null){
            throw new RuntimeException("headerView cannot be null");
        }
        if(!mHeadViews.contains(view)){
            mHeadViews.add(view);
            mLastHeaderType++;
            mTypeViewMap.put(mLastHeaderType,view);
            mViewTypeMap.put(view, mLastHeaderType);
            if(hasAnimation){
                notifyItemInserted(mHeadViews.size() - 1);
            }else{
                notifyDataSetChanged();
            }
        }
    }

    public final void removeHeaderView(View view){
        removeHeaderView(view, false);
    }

    public final void removeHeaderView(View view, boolean hasAnimation){
        if(mHeadViews.contains(view)){
            int index = mHeadViews.indexOf(view);
            int viewType = mViewTypeMap.remove(view);
            mHeadViews.remove(index);
            mTypeViewMap.remove(viewType);
            if(hasAnimation){
                notifyItemRemoved(index);
            }else{
                notifyDataSetChanged();
            }
        }
    }

    public int getFooterViewCount(){
        if (footerViewLayout != null)
            return footerViewLayout.getChildCount();
        return 0;
    }

    public void addFooterView(View view){
        if (footerViewLayout != null)
            footerViewLayout.addView(view);
    }

    public void removeFooterView(View view){
        if (footerViewLayout != null)
            footerViewLayout.removeView(view);
    }
    public void removeAllFooterView(){
        if (footerViewLayout != null)
            footerViewLayout.removeAllViews();
    }

    public int getHeadViewCount(){
        return mHeadViews.size();
    }

    private boolean isHeaderViewType(int viewType){
        return viewType >= mMinHeaderType && viewType <= mMaxHeaderType;
    }


    public void setDataList(List<T> dataList){
        mDataList.clear();
        if(dataList != null && !dataList.isEmpty()){
            mDataList.addAll(dataList);
        }
        notifyDataSetChanged();
    }

    public List<T> getDataList(){
        return mDataList;
    }

    /**
     * @param dataListPosition position in dataList
     * @return
     */
    public T getAdapterDataItem(int dataListPosition){
        if(dataListPosition >= 0 && dataListPosition < mDataList.size()){
            return mDataList.get(dataListPosition);
        }
        return null;
    }

    void enableLoadingMore(boolean enable) {
        if (mEnableLoadingMore != enable) {
            mEnableLoadingMore = enable;
            setDataList(new LinkedList<T>(mDataList));
        }
    }

    public void setCustomLoadingView(View loadingView) {
        mCustomLoadingView = loadingView;
        notifyDataSetChanged();
    }

    public View getCustomLoadingView(){
        return mCustomLoadingView;
    }

    @Override
    public final int getItemCount() {
        if (mEnableLoadingMore && mCustomLoadingView != null) {
            return getHeadViewCount() + getAdapterItemCount()+ (getFooterViewCount() == 0? 1 :2);
        } else {
            return getHeadViewCount() + getAdapterItemCount()+(getFooterViewCount() == 0? 0 :1);
        }
    }

    public int getAdapterItemCount(){
        return mDataList.size();
    }
    public boolean hasLoadingView(){
        return mEnableLoadingMore;
    }

    @Override
    public final int getItemViewType(int position) {
        if(position < getHeadViewCount()){
            return mViewTypeMap.get(mHeadViews.get(position));
        }else{
            if (mEnableLoadingMore && position == getItemCount() - 1 && mCustomLoadingView != null) {
                return VIEW_TYPE_FOOTER;
            } else if (position == getItemCount() - ((mEnableLoadingMore && mCustomLoadingView != null)?2:1) && getFooterViewCount() >0){
                return VIEW_TYPE_FOOTER_VIEW;
            } else {
                int adapterItemViewType = getAdapterItemViewType(position - getHeadViewCount());
                checkAdapterItemViewTypeValid(adapterItemViewType);
                return adapterItemViewType;
            }
        }
    }

    private void checkAdapterItemViewTypeValid(int adapterItemViewType){
        if(adapterItemViewType >= 0 && adapterItemViewType <= mMinFooterType ){
            return;
        }
        throw new RuntimeException("adapterItemViewType's value"+adapterItemViewType
                +" is not valid : ,valid value is between 0 ~ Integer.MAX_VALUE-100");
    }

    /**
     * @param dataListPosition position in dataList
     * @return
     */
    public int getAdapterItemViewType(int dataListPosition) {
        return VIEW_TYPE_NORMAL;
    }

    @Override
    public final DmBaseViewHolder<T> onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == VIEW_TYPE_FOOTER){
            return onCreateLoadingViewHolder(parent, viewType);
        }else if (viewType == VIEW_TYPE_FOOTER_VIEW){
            return new DmBaseViewHolder<T>(footerViewLayout);
        }else if(isHeaderViewType(viewType)){
            return onCreateHeaderViewHolder(parent,viewType);
        }else{
            return onCreateAdapterViewHolder(parent, viewType);
        }
    }

    private DmBaseViewHolder<T> onCreateHeaderViewHolder(ViewGroup parent, int viewType) {
        return new DmBaseViewHolder<T>(mTypeViewMap.get(viewType));
    }

    protected DmBaseViewHolder<T> onCreateLoadingViewHolder(ViewGroup parent, int viewType) {
        return new DmBaseViewHolder<T>(mCustomLoadingView);
    }

    public abstract DmBaseViewHolder<T> onCreateAdapterViewHolder(ViewGroup parent, int viewType);

    @Override
    public final void onBindViewHolder(DmBaseViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        if(viewType == VIEW_TYPE_FOOTER){
            onBindLoadingViewHolder(holder, position);
        }else if(isHeaderViewType(viewType)){
            onBindHeaderViewHolder(holder, position);
        }else{
            onBindAdapterViewHolder(holder, position - getHeadViewCount());
        }
    }

    private void onBindHeaderViewHolder(DmBaseViewHolder holder, int position) {

    }

    protected void onBindLoadingViewHolder(DmBaseViewHolder<T> holder, int position) {

    }

    public abstract void onBindAdapterViewHolder(DmBaseViewHolder<T> holder, int dataListPosition);



    //start wrapper for dataChangedObserver


    public final void notifyAdapterItemChanged(int position) {
        notifyItemRangeChanged(getHeadViewCount() + position, 1);
    }

    public final void notifyAdapterItemChanged(int position, Object payload) {
        notifyItemRangeChanged(getHeadViewCount() + position, 1, payload);
    }


    public final void notifyAdapterItemRangeChanged(int positionStart, int itemCount) {
        notifyItemRangeChanged(getHeadViewCount() + positionStart, itemCount);
    }

    public final void notifyAdapterItemRangeChanged(int positionStart, int itemCount, Object payload) {
        notifyItemRangeChanged(getHeadViewCount() + positionStart, itemCount, payload);
    }

    public final void notifyAdapterItemInserted(int position) {
        notifyItemRangeInserted(getHeadViewCount() + position, 1);
    }

    public final void notifyAdapterItemMoved(int fromPosition, int toPosition) {
        notifyItemMoved(getHeadViewCount() + fromPosition, toPosition);
    }

    public final void notifyAdapterItemRangeInserted(int positionStart, int itemCount) {
        notifyItemRangeInserted(getHeadViewCount() + positionStart, itemCount);
    }

    public final void notifyAdapterItemRemoved(int position) {
        notifyItemRangeRemoved(getHeadViewCount() + position, 1);
    }

    public final void notifyAdapterItemRangeRemoved(int positionStart, int itemCount) {
        notifyItemRangeRemoved(getHeadViewCount() + positionStart, itemCount);
    }

    //end wrapper for dataChangedObserver
}
