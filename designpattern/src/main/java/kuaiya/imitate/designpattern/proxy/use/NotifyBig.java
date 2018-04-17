package kuaiya.imitate.designpattern.proxy.use;

import android.app.Notification;
import android.content.Context;
import android.widget.RemoteViews;

import kuaiya.imitate.designpattern.R;

/**
 * 列表里只允许一个大图 256dp
 * api 16 4.0 添加，但是并不能在16，17，18中很好的展现
 */

public class NotifyBig extends Notify{
    public NotifyBig(Context context) {
        super(context);
    }

    @Override
    public void send() {
        Notification n = builder.build();
        n.contentView = new RemoteViews(context.getPackageName(),
                R.layout.remote_notify_proxy_normal);
        n.bigContentView =  new RemoteViews(context.getPackageName(),
                R.layout.remote_notify_proxy_big);
        nm.notify(0,n);
    }

    @Override
    public void cancel() {
        nm.cancel(0);
    }
}
