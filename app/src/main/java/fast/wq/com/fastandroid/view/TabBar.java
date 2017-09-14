package fast.wq.com.fastandroid.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by admin on 2017/9/12.
 */

public class TabBar extends LinearLayout {

    private int mSelectedTab = -1;
    private int mLastSelectedTab = -1;

    public TabBar(Context context) {
        super(context);
    }

    public TabBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        for (int i = 0; i < getTabCount(); i++) {
            getChildTabViewAt(i).setOnClickListener(new TabClickListener(i));
        }
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);

        final int count = getTabCount();
        for (int i = 0; i < count; i++) {
            View child = getChildTabViewAt(i);
            child.setEnabled(enabled);
        }
    }

    public View getChildTabViewAt(int index) {
        return getChildAt(index);
    }

    public int getTabCount() {
        return getChildCount();
    }


    private void setCurrentTab(int index, boolean clicked) {
        if (index < 0 || index >= getTabCount()) {
            return;
        }

        //same click
        if (index == mSelectedTab) {
            if (currentTabClickListener != null)
                currentTabClickListener.onClick(index);
            return;
        }


        //last
        if (mLastSelectedTab != -1) {
            View child = getChildTabViewAt(mLastSelectedTab);
//            View indicator = child.findViewById(R.id.indicator);
//            if (indicator != null) {
//                indicator.setVisibility(View.GONE);
//            }
            child.setSelected(false);
        }

        int lastSelected = mSelectedTab;
        mSelectedTab = index;

        //current
        mLastSelectedTab = index;
        View child = getChildTabViewAt(mSelectedTab);
//        View indicator = child.findViewById(R.id.indicator);
//        if (indicator != null) {
//            indicator.setVisibility(View.VISIBLE);
//        }
        child.setSelected(true);

        if (mSelectionChangedListener != null) {
            mSelectionChangedListener.onTabChanged(index, clicked, lastSelected);
        }
    }


    //listener
    private OnCurrentTabClickListener currentTabClickListener;
    private OnTabChangeListener mSelectionChangedListener;

    private class TabClickListener implements OnClickListener {

        private final int mTabIndex;

        private TabClickListener(int tabIndex) {
            mTabIndex = tabIndex;
        }

        public void onClick(View v) {
            setCurrentTab(mTabIndex, true);
        }
    }

    public interface OnTabChangeListener {
        void onTabChanged(int tabIndex, boolean clicked, int lastTabIndex);
    }

    public interface OnCurrentTabClickListener {
        void onClick(int tabIndex);
    }


    public void setOnTabChangeListener(OnTabChangeListener listener) {
        mSelectionChangedListener = listener;
    }

    public void setCurrentTabClickListener(OnCurrentTabClickListener currentTabClickListener) {
        this.currentTabClickListener = currentTabClickListener;
    }

}
