package fast.wq.com.fastandroid.myinterface;

import android.view.View;


public abstract class SingleClickListener implements View.OnClickListener {

    public static final int MIN_CLICK_DELAY_TIME = 600;
    private long lastClickTime = 0;

    @Override
    public void onClick(View v) {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastClickTime > MIN_CLICK_DELAY_TIME) {
            lastClickTime = currentTime;
            onSingleClick(v);
        } else {
            onDoubleClick(v);
        }
    }

    public abstract void onSingleClick(View v);

    public abstract void onDoubleClick(View v);
}