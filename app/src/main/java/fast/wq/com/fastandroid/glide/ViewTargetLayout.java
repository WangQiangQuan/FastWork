package fast.wq.com.fastandroid.glide;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.ViewTarget;

/**
 * Glide在内部自动帮我们创建的GlideDrawableImageViewTarget就是ViewTarget的子类。
 * 只不过GlideDrawableImageViewTarget被限定只能作用在ImageView上，
 * 而ViewTarget的功能更加广泛，它可以作用在任意的View上。
 */

public class ViewTargetLayout extends LinearLayout {

    private ViewTarget<ViewTargetLayout, GlideDrawable> viewTarget;

    public ViewTargetLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        viewTarget = new ViewTarget<ViewTargetLayout, GlideDrawable>(this) {
            @Override
            public void onResourceReady(GlideDrawable resource, GlideAnimation glideAnimation) {
                ViewTargetLayout myLayout = getView();
                myLayout.setImageAsBackground(resource);
            }
        };
    }

    public ViewTarget<ViewTargetLayout, GlideDrawable> getTarget() {
        return viewTarget;
    }

    public void setImageAsBackground(GlideDrawable resource) {
        setBackground(resource);
    }
}
/**
 MyLayout myLayout;
 myLayout = (MyLayout) findViewById(R.id.background);

 public void loadImage(View view) {
 String url = "http://cn.bing.com/az/hprichbg/rb/TOAD_ZH-CN7336795473_1920x1080.jpg";
 Glide.with(this)
 .load(url)
 .into(myLayout.getTarget());
 }

 */