package kuaiya.imitate.designpattern.proxy.use;

import android.app.Notification;
import android.content.Context;
import android.widget.RemoteViews;

import kuaiya.imitate.designpattern.R;

/**
 * APP全屏幕展示的时候 收到通知，会自动展示与屏幕顶部。
 * api 20 L 添加，
 */

public class NotifyHeadsUp extends Notify{
    public NotifyHeadsUp(Context context) {
        super(context);
    }

    @Override
    public void send() {
        Notification n = builder.build();
        n.contentView = new RemoteViews(context.getPackageName(),
                R.layout.remote_notify_proxy_normal);
        n.bigContentView =  new RemoteViews(context.getPackageName(),
                R.layout.remote_notify_proxy_big);
        n.headsUpContentView= new RemoteViews(context.getPackageName(),
                R.layout.remote_notify_proxy_normal);
        nm.notify(0,n);
    }

    @Override
    public void cancel() {
        nm.cancel(0);
    }
}
