package fast.wq.com.fastandroid.utils;


import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

import java.util.Map;

import fast.wq.com.fastandroid.gloable.GlobalStates;

public class SpUtil {

    public static <T> void saveToLocal(Context mContext, String name,
                                       String key, T t) {
        // TODO
        SharedPreferences sp;
        if (mContext == null) {
            mContext = GlobalStates.globalContext;
        }
        if (name == null)
            sp = getDefaultSharedPreferences(mContext);
        else
            sp = mContext.getSharedPreferences(name, Context.MODE_PRIVATE);
        Editor editor = sp.edit();
        if (t instanceof Boolean)
            editor.putBoolean(key, (Boolean) t);
        if (t instanceof String)
            editor.putString(key, (String) t);
        if (t instanceof Integer)
            editor.putInt(key, (Integer) t);
        if (t instanceof Float)
            editor.putFloat(key, (Float) t);
        if (t instanceof Long)
            editor.putLong(key, (Long) t);
        editor.apply();
    }

    /**
     * 从本地取回数据
     *
     * @param mContext
     * @param name         SharedPreferences名字
     *                     null为getDefaultSharedPreferences;
     * @param key
     * @param defaultValue 默认值
     * @return
     */
    public static <T> T getFromLocal(Context mContext, String name, String key,
                                     T defaultValue) {
        SharedPreferences sp;
        if (name == null)
            sp = getDefaultSharedPreferences(mContext);
        else
            sp = mContext.getSharedPreferences(name, Context.MODE_PRIVATE);
        Map<String, ?> map = sp.getAll();
        if (map == null)
            return defaultValue;

        if (map.get(key) == null)
            return defaultValue;

        return (T) map.get(key);

    }

    public static boolean clearSp(String name, Context mContext) {
        SharedPreferences sp;
        if (name == null)
            sp = getDefaultSharedPreferences(mContext);
        else
            sp = mContext.getSharedPreferences(name, Context.MODE_PRIVATE);
        return sp.edit().clear().commit();
    }

    private static SharedPreferences getDefaultSharedPreferences(Context mContext) {
        return PreferenceManager.getDefaultSharedPreferences(mContext);
    }
}
