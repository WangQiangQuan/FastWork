package fast.wq.com.fastandroid.manager;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.preference.PreferenceManager;
import android.util.Log;

import fast.wq.com.fastandroid.gloable.GlobalStates;
/**
 * Created by admin on 2017/9/3.
 */

public class PreferenceChangeManager implements OnSharedPreferenceChangeListener {
    private static final String TAG = "PreferenceChangeManager";
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private static PreferenceChangeManager instance;
    private Context mContext;

    private PreferenceChangeManager(final Context context) {
        mContext = context;
        init();
        preferences.registerOnSharedPreferenceChangeListener(this);
    }
    private void init() {
        preferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        editor = preferences.edit();
    }

    public synchronized static PreferenceChangeManager getInstance() {
        if (instance == null) {
            instance = new PreferenceChangeManager(GlobalStates.getContext());
        }
        return instance;
    }
    public void registerOnSharedPreferenceChangeListener(
            SharedPreferences.OnSharedPreferenceChangeListener listener) {
        preferences.registerOnSharedPreferenceChangeListener(listener);
    }
    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        Log.d(TAG, "onSharedPreferenceChanged() called with: sharedPreferences = [" + sharedPreferences + "], key = [" + key + "]");
    }


    public String getString(String key, String defValue) {
        return preferences.getString(key, defValue);
    }

    public void setString(String key, String value) {
        editor.putString(key, value);
        editor.apply();
    }

    public int getInt(String key, int defValue) {
        return preferences.getInt(key, defValue);
    }

    public void setInt(String key, int value) {
        editor.putInt(key, value);
        editor.apply();
    }

    public long getLong(String key, long defValue) {
        return preferences.getLong(key, defValue);
    }

    public void setLong(String key, long value) {
        editor.putLong(key, value);
        editor.apply();
    }

    public boolean getBoolean(String key, boolean defValue) {
        return preferences.getBoolean(key, defValue);
    }

    public void setBoolean(String key, boolean value) {
        editor.putBoolean(key, value);
        editor.apply();
    }

}
/**
 *     PreferenceChangeManager.getInstance().registerOnSharedPreferenceChangeListener(new SharedPreferences.OnSharedPreferenceChangeListener() {
@Override
public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
Log.d(TAG, "onSharedPreferenceChanged() called with: sharedPreferences = [" + sharedPreferences + "], key = [" + key + "]");
}
});
 PreferenceChangeManager.getInstance().setString("wang","value");
 */
