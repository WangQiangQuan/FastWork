package fast.wq.com.fastandroid.utils;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
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
}
