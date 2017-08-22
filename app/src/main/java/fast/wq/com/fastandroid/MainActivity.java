package fast.wq.com.fastandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    public static final String GS_HOME_RES_RECORD_URL = "/v3/users/recommend/acc?rUid=%s&rPath=%s&type=%s&to=%s";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String result = getArgUrl(GS_HOME_RES_RECORD_URL,"1");
        Log.d(TAG, "onCreate() called with: result = [" + result + "]");
    }
    public static String getArgUrl(String url, Object... arg) {
        return String.format(url, arg);
    }

}
