package fast.wq.com.fastandroid.thread.dm;

import android.os.Message;
import android.os.SystemClock;
import android.support.v4.util.TimeUtils;

/**
 * Created by Bingchen on 16/4/5.
 */
public class DmMessage {
    public int what;
    public int arg1;
    public int arg2;
    public Object obj;

    Runnable callback;
    long when;
    boolean inUse;
    DmHandler target;
    DmMessage next;

    private static final Object sPoolSync = new Object();
    private static DmMessage sPool;
    private static int sPoolSize = 0;

    private static final int MAX_POOL_SIZE = 50;

    protected void markInUse() {
        inUse = true;
    }

    protected boolean isInUse() {
        return inUse;
    }

    public void copyFrom(Message o) {
        this.what = o.what;
        this.arg1 = o.arg1;
        this.arg2 = o.arg2;
        this.obj = o.obj;
    }

    public void copyFrom(DmMessage o) {
        this.what = o.what;
        this.arg1 = o.arg1;
        this.arg2 = o.arg2;
        this.obj = o.obj;
    }

    protected void recycle() {
        inUse = false;
        what = 0;
        arg1 = 0;
        arg2 = 0;
        obj = null;
        when = 0;
        target = null;
        callback = null;

        synchronized (sPoolSync) {
            if (sPoolSize < MAX_POOL_SIZE) {
                next = sPool;
                sPool = this;
                sPoolSize++;
            }
        }
    }

    public static DmMessage obtain() {
        synchronized (sPoolSync) {
            if (sPool != null) {
                DmMessage m = sPool;
                sPool = m.next;
                m.next = null;
                m.inUse = false; // clear in-use flag
                sPoolSize--;
                return m;
            }
        }
        return new DmMessage();
    }

    public static DmMessage obtain(DmHandler h) {
        DmMessage m = obtain();
        m.target = h;

        return m;
    }

    public static DmMessage obtain(DmHandler h, Runnable callback) {
        DmMessage m = obtain();
        m.target = h;
        m.callback = callback;

        return m;
    }

    public static DmMessage obtain(DmHandler h, int what) {
        DmMessage m = obtain();
        m.target = h;
        m.what = what;

        return m;
    }

    public static DmMessage obtain(DmHandler h, int what, Object obj) {
        DmMessage m = obtain();
        m.target = h;
        m.what = what;
        m.obj = obj;

        return m;
    }

    public static DmMessage obtain(DmHandler h, int what, int arg1, int arg2) {
        DmMessage m = obtain();
        m.target = h;
        m.what = what;
        m.arg1 = arg1;
        m.arg2 = arg2;

        return m;
    }

    public static DmMessage obtain(DmHandler h, int what,
                                   int arg1, int arg2, Object obj) {
        DmMessage m = obtain();
        m.target = h;
        m.what = what;
        m.arg1 = arg1;
        m.arg2 = arg2;
        m.obj = obj;

        return m;
    }

    public static Message toMessage(DmMessage org, Message out) {
        if (out == null){
            out = Message.obtain();
        }
        out.what = org.what;
        out.arg1 = org.arg1;
        out.arg2 = org.arg2;
        out.obj = org.obj;
        return out;
    }

    @Override
    public String toString() {
        return toString(SystemClock.uptimeMillis());
    }

    String toString(long now) {
        StringBuilder b = new StringBuilder();
        b.append("{ when=");
//        TimeUtils.formatDuration(when - now, b);

        if (target != null) {
            if (callback != null) {
                b.append(" callback=");
                b.append(callback.getClass().getName());
            } else {
                b.append(" what=");
                b.append(what);
            }

            if (arg1 != 0) {
                b.append(" arg1=");
                b.append(arg1);
            }

            if (arg2 != 0) {
                b.append(" arg2=");
                b.append(arg2);
            }

            if (obj != null) {
                b.append(" obj=");
                b.append(obj);
            }

            b.append(" target=");
            b.append(target.getClass().getName());
        } else {
            b.append(" barrier=");
            b.append(arg1);
        }

        b.append(" }");
        return b.toString();
    }

    public static class TwoData{
        public Object obj1;
        public Object obj2;

        public TwoData(Object o1, Object o2) {
            obj1 = o1;
            obj2 = o2;
        }
    }

}
