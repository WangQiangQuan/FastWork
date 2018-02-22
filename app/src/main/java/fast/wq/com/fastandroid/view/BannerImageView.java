package fast.wq.com.fastandroid.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

import fast.wq.com.fastandroid.utils.Utils;

/**
 * Created by admin on 2018/2/8.
 */

@SuppressLint("AppCompatCustomView")
public class BannerImageView extends ImageView {
    private static final String TAG = "BannerImageView";
    private int mDx;
    private int mMinDy;

    private RectF mBitmapRectF;
    private Bitmap mBitmap;

    int width;

    public BannerImageView(Context context) {
        super(context);
        width = Utils.getScreenWidth(this.getContext());
    }

    public BannerImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mMinDy = h;
        Drawable drawable = getDrawable();

        if (drawable == null) {
            return;
        }

//        Log.i(TAG, "onSizeChanged: w"+w+"h:"+h+"p="+p);

        mBitmap = drawableToBitamp(drawable);
        mBitmapRectF = new RectF(0, 0,
                w,
                mBitmap.getHeight() * w / mBitmap.getWidth());
    }

    private Bitmap drawableToBitamp(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bd = (BitmapDrawable) drawable;
            return bd.getBitmap();
        }
        int w = drawable.getIntrinsicWidth();
        int h = drawable.getIntrinsicHeight();
        Bitmap bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, w, h);
        drawable.draw(canvas);
        return bitmap;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.i(TAG, "onDraw: ");
        if (mBitmap == null) {
            Drawable drawable = getDrawable();

            if (drawable == null) {
                return;
            }
            mBitmap = drawableToBitamp(drawable);
            mBitmapRectF = new RectF(0, 0,
                    width,
                   100);
        }
        Log.i(TAG, "onDraw2: "+mBitmapRectF);
        canvas.save();
        canvas.translate(0, 0);
        canvas.drawBitmap(mBitmap, null, mBitmapRectF, null);
        canvas.restore();
    }
}
