package fast.wq.com.fastandroid.function.lubotu;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

import fast.wq.com.fastandroid.loop.TimerUtils;

/**
 * 图片轮播
 * https://www.imooc.com/video/13989
 *
 * //好轮子
 * https://www.jianshu.com/p/d229a647e705
 */

public class ImageBannerViewGroup extends ViewGroup {
    private static final String TAG = "ImageBannerViewGroup";
    int childCount;
    int childHeight;
    int childWidth;

    private int x;//代表 第一次按下位置，
    private int index;

    private Scroller mScroller;
    //自动轮播
    //Timer TimerTask +handler
    private boolean isAuto = true;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    private void startTimer(){
        TimerUtils timeUtils = new TimerUtils(1000,new TimerUtils.TimerProcessor(){
            @Override
            public void process() {
                handler.obtainMessage().sendToTarget();
            }
        });
        timeUtils.startTimer();
    }
    //图片的单击事件
    //可以用 up事件 根据index来判断点击的是第几个图片
    public ImageBannerViewGroup(Context context) {
        super(context);
    }

    public ImageBannerViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private void init() {
        mScroller = new Scroller(getContext());
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), 0);
            invalidate();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        childCount = getChildCount();
        if (0 == childCount) {
            setMeasuredDimension(0, 0);
        } else {
            //测量子视图的宽度和高度
            measureChildren(widthMeasureSpec, heightMeasureSpec);

            View view = getChildAt(0);
            childHeight = view.getMeasuredHeight();
            childWidth = view.getMeasuredWidth();
            int width = view.getMeasuredWidth() * childCount;
            setMeasuredDimension(width, childHeight);
        }
    }

    /**
     * @param change viewgroup 位置发生改变true
     */
    @Override
    protected void onLayout(boolean change, int l, int t, int r, int b) {
        if (change) {
            int leftMargin = 0;
            for (int i = 0; i < childCount; i++) {
                View view = getChildAt(i);
                view.layout(leftMargin, 0, leftMargin + childWidth, childHeight);
                leftMargin += childWidth;
            }
        }
    }

    /**
     * 希望viewgroup处理事件
     *
     * @param ev
     * @return
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }

    /**
     * 屏幕图片滑动的过程，就是viewgroup子视图的移动过程。
     * 1利用scrollerto和scrollerby 完成
     * 2利用scroller 对象完成轮播
     *
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                if (!mScroller.isFinished()){
                    mScroller.abortAnimation();
                }

                x = (int) event.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                int movex = (int) event.getX();
                int distance = movex - x;
                //mScrollX和mScrollY都是作为参数的减数
                scrollBy(-distance, 0);
                break;
            case MotionEvent.ACTION_UP:

                int scrollX = getScrollX();
                index = (scrollX + childWidth / 2) / childWidth;
                if (index < 0) {
                    index = 0;
                } else if (index > childCount - 1) {
                    index = childCount - 1;
                }

                //方式2
                int dx = index * childCount;
                mScroller.startScroll(scrollX, 0, dx, 0);
                postInvalidate();
//                scrollTo(index * childCount, 0);
                break;
            case MotionEvent.ACTION_CANCEL:

                break;
            default:
                break;
        }
        return true;
    }
}
