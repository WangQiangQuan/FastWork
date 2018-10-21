package fast.wq.com.fastandroid.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * 定义N列 布局
 */

public class MuilLayout extends ViewGroup {

    private int Colum = 1;

    int hSpace = 10;

    int vSpace = 10;

    int childWidth = 0;
    int childHeight = 0;

    public MuilLayout(Context context) {
        this(context, null);
    }

    public MuilLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MuilLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int modeWidth = MeasureSpec.getMode(widthMeasureSpec);

        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
        int modeHeight = MeasureSpec.getMode(heightMeasureSpec);

        int totalHeight = 0;

        childWidth = sizeWidth / 2;
        childHeight = 0;
        int childCount = this.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = this.getChildAt(i);
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
//            MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
//            childWidth = child.getMeasuredWidth() +lp.leftMargin+lp.rightMargin;
            childHeight = child.getMeasuredWidth();
            if (i % 2 == 0) {
                totalHeight += childHeight;
            }
            LayoutParams lParams = (LayoutParams) child.getLayoutParams();
            lParams.width = childWidth;
            lParams.height = childHeight;

//            lParams.le = (i % 3) * (childWidth + hSpace);
//            lParams.top = (i / 3) * (childWidth + vSpace);
        }
        sizeWidth = sizeWidth / 2;


        setMeasuredDimension(sizeWidth, totalHeight);
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = this.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = this.getChildAt(i);
            MarginLayoutParams lParams = (MarginLayoutParams) child.getLayoutParams();
            child.layout(lParams.leftMargin, lParams.topMargin, lParams.leftMargin + childWidth,
                    lParams.topMargin + childHeight);
        }
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

//    @Override
//    public LayoutParams generateLayoutParams(AttributeSet attrs) {
//        return new LinearLayout.LayoutParams(getContext(), attrs);
//    }
}
