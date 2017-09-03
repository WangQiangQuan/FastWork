package fast.wq.com.fastandroid.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

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
        this.mLayout = this;
    }


    /**
     * addView and delete
     */
    private List<View> mViews;
    private LinearLayout mLayout;
    public void addItemView(View view){
        if (mViews == null){
            mViews = new ArrayList<>();
        }
        mViews.add(view);
        mLayout.addView(view);

    }
    public void removeAllItemView(){
        if (mViews != null){
            for (int i=0 ;i<mViews.size();i++){
                mLayout.removeView(mViews.get(i));
            }
            mViews.clear();
        }
    }

}
