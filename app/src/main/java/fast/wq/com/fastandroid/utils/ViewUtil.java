package fast.wq.com.fastandroid.utils;

import android.view.View;

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

}
