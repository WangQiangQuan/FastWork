package fast.wq.com.fastandroid.anim;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.view.View;
import android.widget.ImageView;

/**
 * ObjectAnimator: 作用于某一个控件的某一个属性
 * 和
 * ValueAnimator ： 数值发生器
 */

public class AnimatorUtils {
    //谷歌提供的属性都可以替换 拥有get和set方法
    private static final String x = "translationX";
    private static final String y = "translationY";
    private static final String rotation = "rotation";
    private static final String alpha = "alpha";//0->1 不可见到可见

    /**
     * 第一重境界
     * ObjectAnimator
     */
    private void objAnimator(ImageView imageView) {
        ObjectAnimator
                .ofFloat(imageView, "translationX", 0F, 200F)
                .setDuration(1000)
                .start();


    }

    /**
     * 同时操作
     * 可见这几个方法是异步方法。
     *
     * @param imageView
     */
    private void concurrently(ImageView imageView) {
        ObjectAnimator
                .ofFloat(imageView, x, 0F, 200F)
                .setDuration(1000)
                .start();
        ObjectAnimator
                .ofFloat(imageView, y, 0F, 200F)
                .setDuration(1000)
                .start();
        ObjectAnimator
                .ofFloat(imageView, rotation, 0F, 200F)
                .setDuration(1000)
                .start();
    }

    /**
     * 对concurrently 进行优化
     * ofPropertyValuesHolder 同时作用多个属性
     *
     * @param View
     */
    private void propertyValuesHolder(View view) {
        PropertyValuesHolder p1 = PropertyValuesHolder.ofFloat(x, 0f, 200f);
        PropertyValuesHolder p2 = PropertyValuesHolder.ofFloat(y, 0f, 200f);
        PropertyValuesHolder p3 = PropertyValuesHolder.ofFloat(rotation, 0f, 200f);
        ObjectAnimator.ofPropertyValuesHolder(view, p1, p2, p3)
                .setDuration(1000)
                .start();
        ;
    }

    /**
     * AnimatorSet
     * 可以有更加丰富的实现方法。
     * playTogether
     * playSequentially
     * play with
     * play after/before
     * @param view
     */
    private void anmiteSet(View view) {
        ObjectAnimator a1 = ObjectAnimator
                .ofFloat(view, x, 0F, 200F)
                .setDuration(1000);
        ObjectAnimator a2 = ObjectAnimator
                .ofFloat(view, y, 0F, 200F)
                .setDuration(1000);
        ObjectAnimator a3 = ObjectAnimator
                .ofFloat(view, rotation, 0F, 200F)
                .setDuration(1000);
        AnimatorSet set = new AnimatorSet();
        set.playTogether(a1, a2, a3);
//        set.playSequentially(a1, a2, a3);
        set.play(a2).with(a3);
        set.play(a1).after(a2);
        set.setDuration(1000);
        set.start();
    }
    /**
     * 监听
     */

    public void onClickL(View view){
        ObjectAnimator a1 = ObjectAnimator
                .ofFloat(view, alpha, 0f, 1f)
                .setDuration(1000);
        a1.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
            }
        });
        a1.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        a1.start();
    }
}
