package fast.wq.com.fastandroid.anim;

import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.graphics.PointF;
import android.view.View;
import android.widget.Button;

/**
 * 产生一些值
 * ObjectAnimator extends ValueAnimator
 */

public class ValueAnimtorUtils {
    /**
     * 没有制定插值器，默认线性
     * @param btn
     */
    private void valueTest(final Button btn){
        ValueAnimator animator = ValueAnimator.ofInt(0,100);
        animator.setDuration(5000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Integer value = (Integer) animation.getAnimatedValue();
                btn.setText(value.toString());
            }
        });
        animator.start();
    }

    private void objValue(){
        ValueAnimator animator = ValueAnimator.ofObject(new TypeEvaluator() {
            /**
             *
             * @param fraction 时间因子 0-》1
             * @param startValue
             * @param endValue
             * @return
             */
            @Override
            public Object evaluate(float fraction, Object startValue, Object endValue) {
                return null;
            }
        });


        //可以利用泛型 来操作
        ValueAnimator animator2 = ValueAnimator.ofObject(new TypeEvaluator<PointF>() {
            @Override
            public PointF evaluate(float fraction, PointF startValue, PointF endValue) {
                return null;
            }
        });
    }
}
