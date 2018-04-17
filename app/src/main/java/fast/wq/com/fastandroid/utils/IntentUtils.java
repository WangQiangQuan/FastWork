package fast.wq.com.fastandroid.utils;

import android.content.Intent;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * 传参数 0.5M以下
 */

public class IntentUtils {

    public  static <T extends Serializable> void putSerial(Intent mIntent , String key,T t){
        mIntent.putExtra(key, t);
    }

    public static Serializable getSerial(Intent mIntent,String key){
        return mIntent.getSerializableExtra(key);
    }

    public  static <T extends Parcelable> void putParcelable(Intent mIntent, String key, T t){
        mIntent.putExtra(key, t);
    }

    public static Parcelable getParcelable(Intent mIntent,String key){
        return mIntent.getParcelableExtra(key);
    }
}
