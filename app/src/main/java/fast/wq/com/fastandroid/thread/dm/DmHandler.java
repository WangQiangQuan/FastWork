package fast.wq.com.fastandroid.thread.dm;

import android.os.SystemClock;

/**
 * Created by Bingchen on 16/4/5.
 */
public class DmHandler {
    final Callback mCallback;
    boolean isExecuting;

    DmHandlerThreadPool mThreadPool;

    public interface Callback {
        public boolean handleMessage(DmMessage msg);
    }

    public DmHandler() {
        this(null);
    }

    public DmHandler(Callback callback) {
        mCallback = callback;
        mThreadPool = DmHandlerThreadPool.inst;
    }

    public void handleMessage(DmMessage msg) {

    }

    public void dispatchMessage(DmMessage msg) {
        if (msg.callback != null) {
            handleCallback(msg);
        } else {
            if (mCallback != null) {
                if (mCallback.handleMessage(msg)) {
                    return;
                }
            }
            handleMessage(msg);
        }
    }

    private static void handleCallback(DmMessage message) {
        message.callback.run();
    }

    public boolean sendMessageAtTime(DmMessage msg, long uptimeMillis) {
        return enqueueMessage(msg, uptimeMillis);
    }

    public final DmMessage obtainMessage() {
        return DmMessage.obtain(this);
    }

    public final DmMessage obtainMessage(int what) {
        return DmMessage.obtain(this, what);
    }

    public final DmMessage obtainMessage(int what, Object obj) {
        return DmMessage.obtain(this, what, obj);
    }

    public final DmMessage obtainMessage(int what, int arg1, int arg2) {
        return DmMessage.obtain(this, what, arg1, arg2);
    }

    public final DmMessage obtainMessage(int what, int arg1, int arg2, Object obj) {
        return DmMessage.obtain(this, what, arg1, arg2, obj);
    }

    public final boolean post(Runnable r) {
        return sendMessageDelayed(getPostMessage(r), 0);
    }

    public final boolean postAtTime(Runnable r, long uptimeMillis) {
        return sendMessageAtTime(getPostMessage(r), uptimeMillis);
    }

    public final boolean postAtTime(Runnable r, Object token, long uptimeMillis) {
        return sendMessageAtTime(getPostMessage(r, token), uptimeMillis);
    }

    public final boolean postDelayed(Runnable r, long delayMillis) {
        return sendMessageDelayed(getPostMessage(r), delayMillis);
    }

    public final boolean postAtFrontOfQueue(Runnable r) {
        return sendMessageAtFrontOfQueue(getPostMessage(r));
    }

    public final boolean sendMessageAtFrontOfQueue(DmMessage msg) {
        return enqueueMessage(msg, 0);
    }

    public final void removeCallbacks(Runnable r) {
        mThreadPool.removeMessages(this, r, null);
    }

    public final void removeCallbacks(Runnable r, Object token) {
        mThreadPool.removeMessages(this, r, token);
    }

    public final boolean sendMessage(DmMessage msg) {
        return sendMessageDelayed(msg, 0);
    }

    public final boolean sendEmptyMessage(int what) {
        return sendEmptyMessageDelayed(what, 0);
    }

    public final boolean sendEmptyMessageDelayed(int what, long delayMillis) {
        DmMessage msg = DmMessage.obtain();
        msg.what = what;
        return sendMessageDelayed(msg, delayMillis);
    }

    public final boolean sendEmptyMessageAtTime(int what, long uptimeMillis) {
        DmMessage msg = DmMessage.obtain();
        msg.what = what;
        return sendMessageAtTime(msg, uptimeMillis);
    }

    public final boolean sendMessageDelayed(DmMessage msg, long delayMillis) {
        if (delayMillis < 0) {
            delayMillis = 0;
        }
        return sendMessageAtTime(msg, SystemClock.uptimeMillis() + delayMillis);
    }

    /**
     * Remove any pending posts of messages with code 'what' that are in the
     * message queue.
     */
    public final void removeMessages(int what) {
        mThreadPool.removeMessages(this, what, null);
    }

    /**
     * Remove any pending posts of messages with code 'what' and whose obj is
     * 'object' that are in the message queue.  If <var>object</var> is null,
     * all messages will be removed.
     */
    public final void removeMessages(int what, Object object) {
        mThreadPool.removeMessages(this, what, object);
    }

    /**
     * Remove any pending posts of callbacks and sent messages whose
     * <var>obj</var> is <var>token</var>.  If <var>token</var> is null,
     * all callbacks and messages will be removed.
     */
    public final void removeCallbacksAndMessages(Object token) {
        mThreadPool.removeCallbacksAndMessages(this, token);
    }

    /**
     * Check if there are any pending posts of messages with code 'what' in
     * the message queue.
     */
    public final boolean hasMessages(int what) {
        return mThreadPool.hasMessages(this, what, null);
    }

    /**
     * Check if there are any pending posts of messages with code 'what' and
     * whose obj is 'object' in the message queue.
     */
    public final boolean hasMessages(int what, Object object) {
        return mThreadPool.hasMessages(this, what, object);
    }

    /**
     * Check if there are any pending posts of messages with callback r in
     * the message queue.
     *
     * @hide
     */
    public final boolean hasCallbacks(Runnable r) {
        return mThreadPool.hasMessages(this, r, null);
    }

    private static DmMessage getPostMessage(Runnable r) {
        DmMessage m = DmMessage.obtain();
        m.callback = r;
        return m;
    }

    private static DmMessage getPostMessage(Runnable r, Object token) {
        DmMessage m = DmMessage.obtain();
        m.obj = token;
        m.callback = r;
        return m;
    }

    private boolean enqueueMessage(DmMessage msg, long uptimeMillis) {
        if (mThreadPool == null) {
            RuntimeException e = new RuntimeException(
                    this + " sendMessageAtTime() called with no thread pool");
            return false;
        }
        msg.target = this;

        return mThreadPool.enqueueMessage(msg, uptimeMillis);
    }

}
