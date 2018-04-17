package fast.wq.com.fastandroid.utils;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * Created by admin on 2018/3/20.
 */

public class ViewUtil {
    public static void visiable(View view) {

        if (view != null) {
            if (View.VISIBLE != view.getVisibility()) {
                view.setVisibility(View.VISIBLE);
            }
        }
    }

    public static void gone(View view) {

        if (view != null) {
            if (View.GONE != view.getVisibility()) {
                view.setVisibility(View.GONE);
            }
        }
    }

    public static void invisiable(View view) {

        if (view != null) {
            if (View.INVISIBLE != view.getVisibility()) {
                view.setVisibility(View.INVISIBLE);
            }
        }
    }


    private void EditaddLis(EditText searchEdit){
        searchEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence text, int start, int count, int after) {
                Log.i("wang", "beforeTextChanged: "+
                        "输入前字符串 [ " + text.toString() + " ]起始光标 [ " + start + " ]结束偏移量  [" + after + " ]");
            }

            @Override
            public void onTextChanged(CharSequence text, int start, int before, int count) {
                Log.i("wang", "onTextChanged: "+
                        "输入后字符串 [ " + text.toString() + " ] 起始光标 [ " + start + " ] 输入数量 [ " + count+" ]");
            }

            @Override
            public void afterTextChanged(Editable text) {
                Log.i("wang", "afterTextChanged: "+
                        "输入结束后的内容为 [" + text.toString()+" ] 即将显示在屏幕上");
            }
        });
    }

    /**
     * 通知父布局占用宽高
     *  view.getMeasuredHeight() 才可以取到数值
     * @param view
     */
    private void measureView(View view) {
        ViewGroup.LayoutParams p = view.getLayoutParams();
        if (p == null) {
            p = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT
                    , ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        /**
         * spec–viewGroup的属性，measureChildren传递过来的参数
         padding–viewGroup的padding或者margin
         childDimension–childView想要的大小
         */
        int width = ViewGroup.getChildMeasureSpec(0,0,p.width);
        int height;
        int tempHeight = p.width;
        if (tempHeight >0){
            height = View.MeasureSpec.makeMeasureSpec(tempHeight, View.MeasureSpec.EXACTLY);
        }else {
            height = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        }
        view.measure(width,height);
    }
}
