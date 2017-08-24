package fast.wq.com.fastandroid.dialog.dialogimpl;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import fast.wq.com.fastandroid.dialog.base.DmBaseDialog;
import fast.wq.com.fastandroid.dialog.base.DmViewConvertListener;
import fast.wq.com.fastandroid.dialog.base.DmViewHolder;

/**
 * Created by admin on 2017/8/24.
 */

/**
 NormalDialog.init().setLayoutId(R.layout.share_layout).
 setConvertListener(new DmViewConvertListener() {
@Override
public void convertView(DmViewHolder holder, DmBaseDialog dialog) {
holder.setOnClickListener(R.id.wechat, new View.OnClickListener() {
@Override
public void onClick(View v) {
Toast.makeText(MainActivity.this, "分享成功", Toast.LENGTH_SHORT).show();
}
});
}
})
 .setDimAmount(0.3f)
 .setShowBottom(true)
 .setAnimStyle(R.style.EnterExitAnimation)
 .show(getSupportFragmentManager());
 */
public class NormalDialog extends DmBaseDialog
{

    private DmViewConvertListener mDmViewConvertListener;
    public static NormalDialog init() {
        return new NormalDialog();
    }
    @Override
    public int intLayoutId() {
        return layoutId;
    }

    @Override
    public void convertView(DmViewHolder holder, DmBaseDialog dialog) {

        if (mDmViewConvertListener != null) {
            mDmViewConvertListener.convertView(holder,dialog);
        }
    }
    public NormalDialog setLayoutId(@LayoutRes int layoutId) {
        this.layoutId = layoutId;
        return this;
    }

    public NormalDialog setConvertListener(DmViewConvertListener convertListener) {
        this.mDmViewConvertListener = convertListener;
        return this;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            mDmViewConvertListener = (DmViewConvertListener) savedInstanceState.getSerializable("listener");
        }
    }

    /**
     * 保存接口
     *
     * @param outState
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("listener", mDmViewConvertListener);
    }
}
