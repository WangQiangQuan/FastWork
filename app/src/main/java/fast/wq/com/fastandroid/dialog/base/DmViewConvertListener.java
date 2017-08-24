package fast.wq.com.fastandroid.dialog.base;

import java.io.Serializable;

public interface DmViewConvertListener extends Serializable {
    long serialVersionUID = System.currentTimeMillis();

    void convertView(DmViewHolder holder, DmBaseDialog dialog);
}
