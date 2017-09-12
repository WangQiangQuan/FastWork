package fast.wq.com.fastandroid.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import fast.wq.com.fastandroid.R;


/**
 * Created by xsk on 2017/2/24.
 */

public class LoadingView extends RelativeLayout {

    private final View loadingLayout;
    private final View failLayout;

    public LoadingView(Context context) {
        this(context,null);
    }

    public LoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflate(context , R.layout.loading_view_layout , this);
        ((TextView)findViewById(R.id.tv_loading)).setText(R.string.dm_progress_loading);
        ((TextView)findViewById(R.id.tv_fail)).setText(R.string.load_fail);
        loadingLayout = findViewById(R.id.loading_layout);
        failLayout = findViewById(R.id.fail_layout);
        failLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                failLayout.setVisibility(GONE);
                loadingLayout.setVisibility(VISIBLE);
                if (listener != null) {
                    listener.retry();
                }
            }
        });
    }

    public void completeLoad(){
        setVisibility(GONE);
    }

    public void errorLoad(){
        loadingLayout.setVisibility(GONE);
        failLayout.setVisibility(VISIBLE);
    }

    private OnRetryListener listener;

    public void setOnRetryListener(OnRetryListener listener) {
        this.listener = listener;
    }

    public interface OnRetryListener{
        void retry();
    }

}
