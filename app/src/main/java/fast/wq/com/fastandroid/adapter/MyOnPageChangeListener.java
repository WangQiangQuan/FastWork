package fast.wq.com.fastandroid.adapter;

import android.support.v4.view.ViewPager;

/**
 * Created by admin on 2017/9/4.
 */

public class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {
    /**
     * arg0 :当前页面，及你点击滑动的页面
     * <p>
     * arg1:当前页面偏移的百分比
     * <p>
     * arg2:当前页面偏移的像素位置
     *
     * @param position
     * @param positionOffset
     * @param positionOffsetPixels
     */
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    /**
     * 此方法是页面跳转完后得到调用，arg0是你当前选中的页面的Position（位置编号）。
     *
     * @param position
     */
    @Override
    public void onPageSelected(int position) {

    }

    /**
     * 有三种状态（0，1，2）。arg0 ==1的时辰默示正在滑动，arg0==2的时辰默示滑动完毕了，arg0==0的时辰默示什么都没做。
     *
     * log -> 1,2,0
     * @param state
     */
    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
