package fast.wq.com.fastandroid.utils;

import android.text.TextUtils;

/**
 * Created by admin on 2017/8/28.
 */

public class StringUtils {
    public static String getArgUrl(String url, Object... arg) {
        return String.format(url, arg);
    }

    public String getSuffix(String fileName) {
        String suffix = "";
        if (!TextUtils.isEmpty(fileName) && fileName.contains(".")) {
            suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        }
        return suffix;
    }

    /**
     *  http://downloadb.dewmobile.net/z/qiangjing13.apk
     *  qiangjing13.apk
     */

    public static String getPageName(String url) {
        String result = "";
        if (!TextUtils.isEmpty(url) && url.contains("/")) {
            result = url.substring(url.lastIndexOf("/") + 1);
        }
        return result;
    }

}
