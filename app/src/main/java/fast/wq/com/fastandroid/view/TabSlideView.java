package fast.wq.com.fastandroid.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

/**
 * Created by admin on 2017/9/4.
 */

public class TabSlideView extends HorizontalScrollView {
    public TabSlideView(Context context) {
        this(context,null);
    }

    public TabSlideView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TabSlideView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private LinearLayout tabsContainer;

    private void init(Context context) {
        tabsContainer = new LinearLayout(context);
        tabsContainer.setOrientation(LinearLayout.HORIZONTAL);
        tabsContainer.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        addView(tabsContainer);
    }
}
