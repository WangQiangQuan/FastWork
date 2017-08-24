package fast.wq.com.fastandroid.dialog.base;

//import android.app.DialogFragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import fast.wq.com.fastandroid.R;
import fast.wq.com.fastandroid.utils.Utils;

/**
 * Created by admin on 2017/8/24.
 */

public abstract class DmBaseDialog extends DialogFragment {
    private Context mContext;
    private static final String MARGIN = "margin";
    private static final String WIDTH = "width";
    private static final String HEIGHT = "height";
    private static final String DIM = "dim_amount";
    private static final String BOTTOM = "show_bottom";
    private static final String CANCEL = "out_cancel";
    private static final String ANIM = "anim_style";
    private static final String LAYOUT = "layout_id";

    private int margin;//左右边距
    private int width;//宽度
    private int height;//高度
    private float dimAmount = 0.5f;//灰度深浅
    private boolean showBottom;//是否底部显示
    private boolean outCancel = true;//是否点击外部取消

    @StyleRes
    private int animStyle;
    @LayoutRes
    protected int layoutId;

    public abstract int intLayoutId();

    public abstract void convertView(DmViewHolder holder, DmBaseDialog dialog);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.DmDialog);
        layoutId = intLayoutId();
        mContext = this.getActivity();
        //恢复保存的数据
        if (savedInstanceState != null) {
            margin = savedInstanceState.getInt(MARGIN);
            width = savedInstanceState.getInt(WIDTH);
            height = savedInstanceState.getInt(HEIGHT);
            dimAmount = savedInstanceState.getFloat(DIM);
            showBottom = savedInstanceState.getBoolean(BOTTOM);
            outCancel = savedInstanceState.getBoolean(CANCEL);
            animStyle = savedInstanceState.getInt(ANIM);
            layoutId = savedInstanceState.getInt(LAYOUT);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(layoutId, container, false);
        convertView(DmViewHolder.create(view), this);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        initParams();
    }

    /**
     * 屏幕旋转等导致DialogFragment销毁后重建时保存数据
     *
     * @param outState
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(MARGIN, margin);
        outState.putInt(WIDTH, width);
        outState.putInt(HEIGHT, height);
        outState.putFloat(DIM, dimAmount);
        outState.putBoolean(BOTTOM, showBottom);
        outState.putBoolean(CANCEL, outCancel);
        outState.putInt(ANIM, animStyle);
        outState.putInt(LAYOUT, layoutId);
    }

    private void initParams() {
        Window window = getDialog().getWindow();
        if (window != null) {
            //设置dialog进入、退出的动画
            window.setWindowAnimations(animStyle);
            WindowManager.LayoutParams lp = window.getAttributes();
            //调节灰色背景透明度[0-1]，默认0.5f
            lp.dimAmount = dimAmount;
            //是否在底部显示
            if (showBottom) {
                lp.gravity = Gravity.BOTTOM;
            }
            //设置dialog宽度
            if (width == 0) {
                lp.width = Utils.getScreenWidth(mContext) - 2 * Utils.dp2px(mContext, margin);
            } else {
                lp.width = Utils.dp2px(mContext, width);
            }
            //设置dialog高度
            if (height == 0) {
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
            } else {
                lp.height = Utils.dp2px(mContext, height);
            }

            window.setAttributes(lp);
        }
        setCancelable(outCancel);
    }

    public DmBaseDialog setMargin(int margin) {
        this.margin = margin;
        return this;
    }

    public DmBaseDialog setWidth(int width) {
        this.width = width;
        return this;
    }

    public DmBaseDialog setHeight(int height) {
        this.height = height;
        return this;
    }

    public DmBaseDialog setDimAmount(float dimAmount) {
        this.dimAmount = dimAmount;
        return this;
    }

    public DmBaseDialog setShowBottom(boolean showBottom) {
        this.showBottom = showBottom;
        return this;
    }

    public DmBaseDialog setOutCancel(boolean outCancel) {
        this.outCancel = outCancel;
        return this;
    }

    public DmBaseDialog setAnimStyle(@StyleRes int animStyle) {
        this.animStyle = animStyle;
        return this;
    }

    public DmBaseDialog show(FragmentManager manager) {
        super.show(manager, String.valueOf(System.currentTimeMillis()));
        return this;
    }

    public void disMissDialog() {
        if (this != null && this.getDialog() != null && this.getDialog().isShowing()) {
            this.dismiss();
        }

    }
}
