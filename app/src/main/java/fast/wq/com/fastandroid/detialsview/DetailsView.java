package fast.wq.com.fastandroid.detialsview;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import fast.wq.com.fastandroid.R;

/**
 * https://techblog.toutiao.com/2017/03/21/jiaohu/#comments
 */

public class DetailsView extends ViewGroup {

    Page mPage = null;
    ILifeCycleProvider mILifeCycleProvider;

    public DetailsView(Context context) {
        super(context);
        init((Activity) context);
    }

    public DetailsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init((Activity) context);
    }

    public void setLifeCallback(ILifeCycleProvider mILifeCycleProvider) {
        this.mILifeCycleProvider = mILifeCycleProvider;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int childCount = getChildCount();

        int defaultMeasuredWidth = 0;
        int defaultMeasuredHeight = 0;
        int measuredWidth;
        int measuredHeight;
        if (childCount > 0) {
            measureChildren(widthMeasureSpec, heightMeasureSpec);
            mDragContentView = getChildAt(0);
            defaultMeasuredWidth = mDragContentView.getMeasuredWidth();
            defaultMeasuredHeight = mDragContentView.getMeasuredHeight();
        }
        measuredWidth = View.resolveSize(defaultMeasuredWidth, widthMeasureSpec) + getPaddingLeft() + getPaddingRight();
        measuredHeight = View.resolveSize(defaultMeasuredHeight, heightMeasureSpec) + getPaddingTop() + getPaddingBottom();

        setMeasuredDimension(measuredWidth, measuredHeight);
    }

    @Override
    protected void onLayout(boolean b, int left, int top, int right, int bottom) {
        mDragContentView.layout(left, top, right, bottom);
    }

    View mDragContentView;

    private void init(Activity context) {
        mDragContentView = context.getLayoutInflater().inflate(R.layout.details, null);
        mDragContentView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackClick(view);
            }
        });
        this.addView(mDragContentView);
        mPage = new Page(context) {
            @Override
            public boolean dispatchKeyEvent(KeyEvent event) {
                return DetailsView.this.dispatchKeyEvent(event);
            }

            @Override
            public boolean dispatchTouchEvent(MotionEvent event) {
                return DetailsView.this.dispatchTouchEvent(event);
            }
        };
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        return super.dispatchKeyEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }


    public void onPageShow() {
        mILifeCycleProvider.onPageShow();
    }

    public void onPageResume() {
        mILifeCycleProvider.onPageResume();
    }

    public void onBackClick(View view) {
        onPageResume();
    }
}
