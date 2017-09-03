package fast.wq.com.fastandroid.gloable;

import android.content.Context;

/**
 * Created by admin on 2017/9/3.
 */

public class GlobalStates {
    public static Context globalContext;

    public synchronized static void setContext(Context ctx){
        globalContext = ctx;
  }

    public synchronized static Context getContext(){
        return globalContext;
    }
}
