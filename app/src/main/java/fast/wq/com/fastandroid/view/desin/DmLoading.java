package fast.wq.com.fastandroid.view.desin;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * Created by admin on 2017/12/13.
 */

public class DmLoading  extends FrameLayout implements DmRefreshTrigger{
    public DmLoading(@NonNull Context context) {
        super(context);
    }

    public DmLoading(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onPullDownState(float progress) {

    }

    @Override
    public void onRefreshing() {

    }

    @Override
    public void onReleaseToRefresh() {

    }

    @Override
    public void onComplete() {

    }

    @Override
    public void init() {

    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }
}
