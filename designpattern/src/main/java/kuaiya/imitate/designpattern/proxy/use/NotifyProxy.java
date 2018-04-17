package kuaiya.imitate.designpattern.proxy.use;

import android.content.Context;
import android.os.Build;

import kuaiya.imitate.designpattern.User;

/**
 *  可以用装饰者模式进行优化
 */

public class NotifyProxy extends Notify {
    private Notify notify;
    public NotifyProxy(Context context) {
        super(context);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){

        }else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            notify = new NotifyBig(context);
        }else {
            notify = new NotifyNormal(context);
        }
    }

    @Override
    public void send() {
        notify.send();
    }

    @Override
    public void cancel() {
        notify.cancel();
    }

    public void use(Context m){
        new NotifyProxy(m).send();
    }
}
