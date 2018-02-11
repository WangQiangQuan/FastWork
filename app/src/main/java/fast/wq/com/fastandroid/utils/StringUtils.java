package fast.wq.com.fastandroid.utils;

import android.graphics.Point;
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


    //int w,h
    public static Point parseWh(String text){
        Point m = null;
        if (!TextUtils.isEmpty(text) && text.contains("x")){
            String wh[] =  text.split("x");
            String w = wh[0];
            String h = wh[1];
            int pw = 1;
            int ph = 1;
            if (!TextUtils.isEmpty(w)){
                pw = Integer.parseInt(w);
            }
            if (!TextUtils.isEmpty(h)){
                ph = Integer.parseInt(h);
            }

           m = new Point(pw,ph);
        }
        return m;
    }

    //String、StringBuffer和StringBuilder的区别
    //而StringBuffer是线程安全的,StringBuilder是线程不安全的
    public void append(){
        StringBuilder mStringBuilder=new StringBuilder();
        mStringBuilder.append("abc").append("de");
    }
}
