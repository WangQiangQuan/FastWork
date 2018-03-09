package fast.wq.com.fastandroid.utils;

import android.text.TextUtils;

/**
 * Created by admin on 2018/3/6.
 */

public class ConvertUtils {

    //String转Integer
    public static Integer s2I(String str, int defaultValues) {
        Integer i = null;
        if (!TextUtils.isEmpty(str)) {
            try {
                i = Integer.valueOf(str);
            } catch (NumberFormatException e) {
                i = defaultValues;
                e.printStackTrace();
            }
        } else {
            i = defaultValues;
        }

        return i;
    }
    //String转 int
    public static int s2i(String str, int defaultValues) {
        int i = 0;
        if (!TextUtils.isEmpty(str)) {
            try {
                i = Integer.valueOf(str);
            } catch (NumberFormatException e) {
                i = defaultValues;
                e.printStackTrace();
            }
        } else {
            i = defaultValues;
        }

        return i;
    }
}
