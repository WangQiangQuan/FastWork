package fast.wq.com.fastandroid.detialsview;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import fast.wq.com.fastandroid.R;

/**
 * https://techblog.toutiao.com/2017/03/21/jiaohu/#comments
 */

public class DetailsView extends ViewGroup {

    private Activity mActivity;
    private Page mPage = null;
    private ILifeCycleProvider mILifeCycleProvider;
    private ImageView mImageView;
    private LinearLayout mdetails_linear;

    public DetailsView(Context context) {
        super(context);
        mActivity = (Activity) context;
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
            mRootView = getChildAt(0);
            defaultMeasuredWidth = mRootView.getMeasuredWidth();
            defaultMeasuredHeight = mRootView.getMeasuredHeight();
        }
//        measuredWidth = View.resolveSize(defaultMeasuredWidth, widthMeasureSpec) + getPaddingLeft() + getPaddingRight();
//        measuredHeight = View.resolveSize(defaultMeasuredHeight, heightMeasureSpec) + getPaddingTop() + getPaddingBottom();
//
//        measureChildren(widthMeasureSpec,heightMeasureSpec);
        setMeasuredDimension( getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec),
                getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec));
//        setMeasuredDimension(defaultMeasuredWidth, defaultMeasuredHeight);
    }

    @Override
    protected void onLayout(boolean b, int left, int top, int right, int bottom) {
//         right = left + mRootView.getMeasuredWidth();
//         bottom = top + mRootView.getMeasuredHeight();

        mRootView.layout(left, top, right, bottom);
    }

    View mRootView;

    private void init(Activity context) {
        mRootView = context.getLayoutInflater().inflate(R.layout.details, null);
        mImageView = mRootView.findViewById(R.id.details_image);
        mImageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackClick(view);
            }
        });
        this.addView(mRootView);
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
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if(keyCode == KeyEvent.KEYCODE_BACK) { //监控/拦截/屏蔽返回键
//            Log.i("wang", "按下了back键   onKeyDown(): ");
//            return true;
//        } else if(keyCode == KeyEvent.KEYCODE_MENU) {
//            //监控/拦截菜单键
//        } else if(keyCode == KeyEvent.KEYCODE_HOME) {
//            //由于Home键为系统键，此处不能捕获，需要重写onAttachedToWindow()
//        }
//        return super.onKeyDown(keyCode, event);
//    }
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN){
            if ((event.getKeyCode() == KeyEvent.KEYCODE_BACK)) {
                Log.i("wang", "按下了back键   onKeyDown(): ");
                onBackPresse();
                return false;
            }else if(event.getKeyCode() == KeyEvent.KEYCODE_MENU) {
                Log.i("wang", "按下了KEYCODE_MENU键   KEYCODE_MENU(): ");
                //监控/拦截菜单键
                return false;
            } else if(event.getKeyCode() == KeyEvent.KEYCODE_HOME) {
                //由于Home键为系统键，此处不能捕获，需要重写onAttachedToWindow()
                Log.i("wang", "按下了KEYCODE_HOME键   KEYCODE_HOME(): ");
                return false;
            }
        }

        return super.dispatchKeyEvent(event);
//           return mRootView.dispatchKeyEvent(event);

    }

    private float mTouchOffsetX,mTouchOffsetY;
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            final View decorView = mActivity.getWindow().getDecorView();
            final int top = getStatusBarHeight(mActivity);
            final int left =0;
            mTouchOffsetX = decorView.getScrollX() - left;
            mTouchOffsetY = decorView.getScrollY() - top;
        }
        event.offsetLocation(mTouchOffsetX, mTouchOffsetY);
        return mRootView.dispatchTouchEvent(event);
//        return super.dispatchTouchEvent(event);
    }

    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public void onPageShow() {
        mILifeCycleProvider.onPageShow();
    }

    public void onPagedismiss() {
        mPage.onDestroy();
        mILifeCycleProvider.onPageResume();

    }

    public void onBackClick(View view) {
        onPagedismiss();
    }



    private int durtionTime = 500;
    private int sourceY = 0;
    private int sourceHeight = 0;
    public void setEnterAnimation(ImageView transitionView){
        int[] screenLocation = new int[2];
        transitionView.getLocationOnScreen(screenLocation);
        sourceY = screenLocation[1];
        sourceHeight = transitionView.getHeight();


        mImageView.setBackgroundResource(R.drawable.sunny);

        mdetails_linear = mRootView.findViewById(R.id.details_linear);

        ObjectAnimator mObjectAnimator =  ObjectAnimator.ofFloat(mImageView,"translationY",screenLocation[1],0);
        mObjectAnimator.setDuration(durtionTime);
        mObjectAnimator.start();

        performAnim2(mdetails_linear);
        performAnimMove(mdetails_linear);
//        intent.putExtra("transleft", screenLocation[0]);
//        intent.putExtra("transtop", screenLocation[1]);
//        intent.putExtra("transwidth", transitionView.getWidth());
//        intent.putExtra("transheight", transitionView.getHeight());
    }

    private boolean show = false;
    private int height = 600;
    private void performAnim2(final View view){
        //View是否显示的标志
//        show = !show;
        show = true;
//        属性动画对象
        ValueAnimator va ;
        if(show){
            //显示view，高度从0变到height值
            va = ValueAnimator.ofInt(0,height);
        }else{
            //隐藏view，高度从height变为0
            va = ValueAnimator.ofInt(height,0);
        }
        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {

                //获取当前的height值
                int h =(Integer)valueAnimator.getAnimatedValue();
                Log.i("wangQ", "onAnimationUpdate: h = "+DetailsView.this.getWidth());
                //动态更新view的高度
                view.getLayoutParams().height = h;
                view.requestLayout();
            }
        });
        va.setDuration(durtionTime);
        //开始动画
        va.start();
    }
    private void performAnimMove(final View view){
//       int height = Utils.dp2px(view.getContext(),200);
        int height = sourceHeight;
        ObjectAnimator mObjectAnimator =  ObjectAnimator.ofFloat(view,"translationY",sourceY,0);
        mObjectAnimator.setDuration(durtionTime);
        mObjectAnimator.start();
    }

    private void onBackPresse(){
        onPagedismiss();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        mPage.onCreate();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mPage.onDestroy();
    }
}
