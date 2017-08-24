package fast.wq.com.fastandroid.dialog.dialogimpl;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import fast.wq.com.fastandroid.R;
import fast.wq.com.fastandroid.dialog.base.DmBaseDialog;
import fast.wq.com.fastandroid.dialog.base.DmViewHolder;

/**
 * Created by admin on 2017/8/24.
 */

/**
 ConfirmDialog.init().setType("2")
 .setMargin(60)
 .setOutCancel(false)
 .show(getSupportFragmentManager());
 */
public class ConfirmDialog extends DmBaseDialog
{
    private String type;

    public static ConfirmDialog init() {
        return new ConfirmDialog();
    }

    public ConfirmDialog setType(String type){

        this.type = type;
        Bundle bundle = new Bundle();
        bundle.putString("type", type);
        this.setArguments(bundle);
        return this;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle == null) {
            return;
        }
        type = bundle.getString("type");
    }

    @Override
    public int intLayoutId() {
         return R.layout.confirm_layout;
    }

    @Override
    public void convertView(DmViewHolder holder, final DmBaseDialog dialog) {
        if ("1".equals(type)) {
            holder.setText(R.id.title, "提示");
            holder.setText(R.id.message, "您已支付成功！");

        } else if ("2".equals(type)) {
            holder.setText(R.id.title, "警告");
            holder.setText(R.id.message, "您的账号已被冻结！");
        }
        holder.setOnClickListener(R.id.cancel, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.disMissDialog();
            }
        });

        holder.setOnClickListener(R.id.ok, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.disMissDialog();
            }
        });
    }
}
