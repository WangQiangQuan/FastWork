package fast.wq.com.fastandroid.view.desin;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.BounceInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;

/**
 * Created by lbf on 2016/7/24.
 */
public class CustomProgressDrawable extends MaterialProgressDrawable {

    //    旋转因子，调整旋转速度
    private static final int ROTATION_FACTOR = 3 * 360;
    //    加载时的动画
    private Animation mAnimation;
    private View mParent;
    private Bitmap mBitmap;
    //    旋转角度
    private float rotation;
    private Paint paint;
    private Activity mContext;

    public CustomProgressDrawable(Context context, View parent) {
        super(context, parent);
        mParent = parent;
        paint = new Paint();
        this.mContext = (Activity) context;
//        setupAnimation();
        setBackgroundColor(Color.WHITE);
    }

    private void setupAnimation() {
//        初始化旋转动画
        mAnimation = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                setProgressRotation(-interpolatedTime);
            }
        };
        mAnimation.setDuration(5000);
//        无限重复
        mAnimation.setRepeatCount(Animation.INFINITE);
        mAnimation.setRepeatMode(Animation.RESTART);
//        均匀转速
        mAnimation.setInterpolator(new LinearInterpolator());
    }

    private void restoreAnimation() {
        Rect bound = getBounds();
        mSave.rotate(0, bound.exactCenterX(), bound.exactCenterY());
        Rect src = new Rect(0, 0, mBitmap.getWidth(), mBitmap.getHeight());
        mSave.drawBitmap(mBitmap, src, bound, paint);
    }

    private void startFromZero() {


        View view = null;
        if (mParent instanceof CustomSwipeRefreshLayout) {
            view = ((CustomSwipeRefreshLayout) mParent).getCircleView();
        }
        if (mParent instanceof DmSwipeRefreshLayout) {
            view = ((DmSwipeRefreshLayout) mParent).getCircleView();
        }
//        ObjectAnimator anim1 = ObjectAnimator.ofFloat(view, "rotation", 90f, 270f);
//        ObjectAnimator anim2 = ObjectAnimator.ofFloat(view, "rotation", 270f, 90f);

//        mSave.rotate(-rotation);

        Log.i("wang", "startFromZero: getRotation=" + view.getRotation() + "rotation:" + rotation);
//        float from1 = 0 - rotation;
//        ObjectAnimator anim1 = ObjectAnimator.ofFloat(view, "rotation", 0f, 180f);
//        ObjectAnimator anim2 = ObjectAnimator.ofFloat(view, "rotation", 180f,360f);
//        ObjectAnimator anim3 = ObjectAnimator.ofFloat(view, "rotation", 0f, 180f);
//        ObjectAnimator anim4 = ObjectAnimator.ofFloat(view, "rotation", 180f,360f);

        int dex = 45;

        if (view.getRotation() == 0) {
            dex = 0;
        } else {
//            dex = 45;
            dex = 0;
        }
        ObjectAnimator anim0 = ObjectAnimator.ofFloat(view, "rotation", 0f , 0);
        ObjectAnimator anim1 = ObjectAnimator.ofFloat(view, "rotation", 0f - dex, 180f - dex);
        ObjectAnimator anim2 = ObjectAnimator.ofFloat(view, "rotation", 180f - dex, 360f - dex);
        ObjectAnimator anim3 = ObjectAnimator.ofFloat(view, "rotation", 0f - dex, 180f - dex);
        ObjectAnimator anim4 = ObjectAnimator.ofFloat(view, "rotation", 180f - dex, 360f - dex);
        ObjectAnimator anim5 = ObjectAnimator.ofFloat(view, "rotation", 0f , 0);

        int time = 1000;
        anim0.setDuration(0);
        anim1.setDuration(time);
        anim2.setDuration(time);
        anim3.setDuration(time);
        anim4.setDuration(time);
        anim5.setDuration(0);

        anim1.setInterpolator(new BounceInterpolator());
        anim2.setInterpolator(new BounceInterpolator());
        anim3.setInterpolator(new BounceInterpolator());
        anim4.setInterpolator(new BounceInterpolator());

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(anim0,anim1, anim2, anim3, anim4,anim5);

        animatorSet.start();


    }

    @Override
    public void start() {
//        mParent.startAnimation(mAnimation);
        startFromZero();
//        Timer timer = new Timer();
//        TimerTask task = new TimerTask() {
//            @Override
//            public void run() {
//                // TODO Auto-generated method stub
//                mContext.runOnUiThread(new Runnable() {
//                    public void run() {
//                        startFromZero();
//                    }
//                });
//            }
//        };
//        timer.schedule(task, 0, 270);
    }

    public void setBitmap(Bitmap mBitmap) {
        this.mBitmap = mBitmap;
    }


    @Override
    public void setProgressRotation(float rotation) {
//        取负号是为了和微信保持一致，下拉时逆时针转加载时顺时针转，旋转因子是为了调整转的速度。
        Log.i("wang", "setProgressRotation: rotation = "+rotation);
        this.rotation = -rotation * ROTATION_FACTOR;
        changeRotation(   this.rotation);
        invalidateSelf();
    }

    private Canvas mSave;

    @Override
    public void draw(Canvas c) {
        Rect bound = getBounds();
//        c.save();

//        c.rotate(rotation, bound.exactCenterX(), bound.exactCenterY());
        Rect src = new Rect(0, 0, mBitmap.getWidth(), mBitmap.getHeight());
        c.drawBitmap(mBitmap, src, bound, paint);
//        c.restore();

    }

    private float oldRotation = 0f;
    private void changeRotation(float rotation){
        View view = null;
        if (mParent instanceof CustomSwipeRefreshLayout) {
            view = ((CustomSwipeRefreshLayout) mParent).getCircleView();
        }
        if (mParent instanceof DmSwipeRefreshLayout) {
            view = ((DmSwipeRefreshLayout) mParent).getCircleView();
        }
        ObjectAnimator anim0 = ObjectAnimator.ofFloat(view, "rotation", oldRotation , rotation);
        anim0.setDuration(0);
        anim0.start();
        oldRotation = rotation;
    }
}
