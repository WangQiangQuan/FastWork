package kuaiya.imitate.designpattern.template.use;

import android.text.TextUtils;

/**
 * Created by wangqiang on 2018/12/30.
 */

public class AbsLoader implements ILoader {
    @Override
    public void loadUrl(RequestParms parms) {
        //1 从缓存中拿到路径
        String path = "";
        //没有缓存
        if (TextUtils.isEmpty(path)) {
            //2显示加载中

            //3网络请求

            //4加入缓存

            deliveryToUiThread(parms,path);
        } else {
            parms.justCacheInMem = true;
        }
    }

    private void deliveryToUiThread(RequestParms parms, String path) {
    }
}
