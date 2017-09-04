package fast.wq.com.fastandroid.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import fast.wq.com.fastandroid.utils.Utils;


/**
 * Android仿微博导航条
 */

public class DynamicView extends View{
    private float mStartX, mStopX;
    private Paint mPaint;

    private RectF mRectf = new RectF(mStartX,0,mStartX,0);
    private int height = 10;
    private int round = 5;

    public DynamicView(Context context) {
        this(context,null);
    }

    public DynamicView(Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    public DynamicView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();

    }
    private void init(){
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(5);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setShader(new LinearGradient(0,100,
                Utils.getScreenWidth(getContext()),100, Color.parseColor("#ffc125"),
                Color.parseColor("#ff4500"), Shader.TileMode.MIRROR));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        heightMeasureSpec = MeasureSpec.makeMeasureSpec(20,MeasureSpec.getMode(heightMeasureSpec));
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mRectf.set(mStartX,0, mStopX,height);
        canvas.drawRoundRect(mRectf,round,round,mPaint);
    }

    public void updateView(float startX,float stopX){
        mStartX = startX;
        mStopX = stopX;
        invalidate();
    }
}
