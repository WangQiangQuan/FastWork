package fast.wq.com.fastandroid.view.recyclerview;

import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by admin on 2017/12/7.
 */

public class MyItemDecoration extends RecyclerView.ItemDecoration {

    /**
     *   @param mSpace     item间的间距
     *   @param mEdgeSpace 边距(padding)
     */
    private int mEdgeSpace = 30;
    private int mSpace = 0;


    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
//        super.getItemOffsets(outRect, view, parent, state);
        RecyclerView.LayoutManager manager = parent.getLayoutManager();
        int childPosition = parent.getChildAdapterPosition(view);
        int itemCount = parent.getAdapter().getItemCount();
        if (manager != null) {
            if (manager instanceof GridLayoutManager) {

            } else if (manager instanceof LinearLayoutManager) {
                setLinearOffset(((LinearLayoutManager) manager).getOrientation(), outRect, childPosition, itemCount);
            }
        }

    }

    private void setLinearOffset(int orientation, Rect outRect, int childPosition, int itemCount) {
        if (orientation == LinearLayoutManager.HORIZONTAL) {
            if (childPosition == 0) {
                // 第一个要设置PaddingLeft
                outRect.set(mEdgeSpace, 0, mSpace, 0);
            } else if (childPosition == itemCount - 1) {
                // 最后一个设置PaddingRight
                outRect.set(0, 0, mEdgeSpace, 0);
            } else {
                outRect.set(0, 0, mSpace, 0);
            }
        } else {
            if (childPosition == 0) {
                // 第一个要设置PaddingTop
                outRect.set(0, mEdgeSpace, 0, mSpace);
            } else if (childPosition == itemCount - 1) {
                // 最后一个要设置PaddingBottom
                outRect.set(0, 0, 0, mEdgeSpace);
            } else {
                outRect.set(0, 0, 0, mSpace);
            }
        }
    }


}
