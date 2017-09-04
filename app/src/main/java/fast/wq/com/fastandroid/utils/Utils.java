package fast.wq.com.fastandroid.utils;

import android.content.Context;
import android.text.TextPaint;
import android.util.DisplayMetrics;
import android.widget.TextView;

public class Utils {
    public static int dp2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
    public static int getScreenWidth(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return displayMetrics.widthPixels;
    }

    public static int getScreenHeight(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return displayMetrics.heightPixels;
    }

    public static float getTextViewLength(TextView textView) {
        TextPaint paint = textView.getPaint();
        return paint.measureText(textView.getText().toString());
    }

    public static float getTextViewLength(TextView textView, float textSize) {
        TextPaint paint = textView.getPaint();
        paint.setTextSize(textSize);
        return paint.measureText(textView.getText().toString());
    }
}
