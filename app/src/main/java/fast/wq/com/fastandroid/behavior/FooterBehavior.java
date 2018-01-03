package fast.wq.com.fastandroid.behavior;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;

/**
 * http://blog.csdn.net/tabolt/article/details/51821933
 * 1L某个view需要根据监听另一个的行为来控制自己的行为，
 * 2:我们的view需要根据监听CoordinatorLayout中的子view的滚动行为来改变自己的状态
 *
 * NestedScrollView ：
 * http://blog.csdn.net/happy_horse/article/details/54619526
 */

public class FooterBehavior extends CoordinatorLayout.Behavior<View>{
    private float targetY = -1;
    public FooterBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 方式1
     */
    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return dependency instanceof AppBarLayout;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
//        return super.onDependentViewChanged(parent, child, dependency);
        float scaleY = Math.abs(dependency.getY()) / dependency.getHeight();
        child.setTranslationY(child.getHeight() * scaleY);
        return true;
    }


    /**
     * 方式2
     * child：简单点说，就是用到当前CoordinatorLayout的子View，响应此Behavior。
     target：CoordinatorLayout的子View，引起滚动的view，其实child的状态改变是根据target来实现的。
     */
    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child,
                                       View directTargetChild, View target, int nestedScrollAxes) {
        if(targetY == -1){
            targetY = target.getY();
        }
        return (nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
    }

    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View child, View target,
                                  int dx, int dy, int[] consumed) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed);
        float scrooY = targetY - Math.abs(target.getY());
        float scaleY = scrooY / targetY;
        child.setTranslationY(child.getHeight() * scaleY);
    }
}
