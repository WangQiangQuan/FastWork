package fast.wq.com.fastandroid.anim;

import android.util.Log;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

/**
 * Created by wangqiang on 2017/12/19.
 */

public class animUtils {

    private void move(ImageView image){
        TranslateAnimation animation = new TranslateAnimation(0,200,0,0);
        animation.setDuration(1000);
        animation.setFillAfter(true);
        image.startAnimation(animation);
    }
    public static void  anim(int fromLeft,int fromTop,int fromWith,int fromHeight,int toWith,int toHeight){
        Log.i("wang", "anim() called with: fromLeft = [" + fromLeft + "], fromTop = [" + fromTop + "], fromWith = [" + fromWith + "], fromHeight = [" + fromHeight + "], toWith = [" + toWith + "], toHeight = [" + toHeight + "]");

        int pivotX=0,pivotY=0;

        if (fromLeft<20 &&fromTop<toHeight/2 ){
            pivotX = 0;
            pivotY =0;
        }else if(fromLeft>20 && fromTop<toHeight/2){
            pivotX = toWith;
            pivotY =0;
        }else if(fromLeft<20 && fromTop>toHeight/2){
            pivotX = 0;
            pivotY =toHeight;
        }
        else if(fromLeft>20 && fromTop>toHeight/2){
            pivotX = toWith;
            pivotY =toHeight;
        }
        ScaleAnimation scaleAnimation = new ScaleAnimation(1,toWith/fromWith,1,toHeight/fromHeight,pivotX,pivotY);
    }
}
