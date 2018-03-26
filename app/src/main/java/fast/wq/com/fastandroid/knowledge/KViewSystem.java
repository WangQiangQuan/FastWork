package fast.wq.com.fastandroid.knowledge;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

/**
 * View的工作原理
 */

public class KViewSystem {

    private void test(){

        /**
         * DecoterView 与窗口和本身LayoutParams有关
         */

        /**
         * ViewGroup measureChildWithMargins
         * 可见 子元素MeasureSpec的创建和父容器的 MeasureSpec和 子元素本身的LayoutParams有关
         *
         * measureChildren
         */
        ViewGroup mGroup = null;

        /**
         * View onMeasure
         */
       final View view = null;
        /**
         * View measeure 的生命周期和 Activity 并不是同步
         * 1       Activity/view.onWindowFocusChanged();
         * 2post
         * 3ViewTreeObserver
         * 4
         */

        view.post(new Runnable() {
            @Override
            public void run() {

                int width = view.getMeasuredWidth();
            }
        });



    }

    private void viewTreeobserver(final View view){
        ViewTreeObserver observer = view.getViewTreeObserver();
        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                int width = view.getMeasuredWidth();
            }
        });
    }

    private void viewMeasure(View view){

        //match_parent 直接放弃 因为无法知道 parentsize

        //绝对值 100px
        int widthMespec = View.MeasureSpec.makeMeasureSpec(100, View.MeasureSpec.EXACTLY);
        int heightMespec = View.MeasureSpec.makeMeasureSpec(100, View.MeasureSpec.EXACTLY);
        view.measure(widthMespec,heightMespec);
    }
}
