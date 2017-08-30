package fast.wq.com.fastandroid.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by admin on 2017/8/29.
 */

public class TaskLinerLayout extends LinearLayout {
    public TaskLinerLayout(Context context) {
        this(context,null);
    }

    public TaskLinerLayout(Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    public TaskLinerLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        this.setOrientation(LinearLayout.VERTICAL);
    }




}
