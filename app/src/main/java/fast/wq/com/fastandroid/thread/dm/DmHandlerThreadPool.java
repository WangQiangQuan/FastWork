package fast.wq.com.fastandroid.thread.dm;

import android.os.SystemClock;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Bingchen on 16/4/5.
 */
public class DmHandlerThreadPool {

    static DmHandlerThreadPool inst = new DmHandlerThreadPool();

    static final int POOL_SIZE = 2;
    static final int FIXED_SIZE = 1;
    static final int IDLE_TIME = 30000;
    static final long ONE_YEAR = 365 * 24 * 3600 * 1000L;
    static final int URGENT_SIZE = 4;//紧急模式的最大线程数
    static final int MSG_TIMEOUT = 5000;//消息5秒没有处理则进入紧急模式

    DmMessage mMessages;
    int mThreadCount;

    List<HandlerThread> mThreads;
    Lock lock;

    MessageDispatchThread dispatchThread;

    DmHandlerThreadPool() {
        mThreads = new LinkedList<>();
        lock = new ReentrantLock(true);
    }

    public static void clearAll() {
        inst.clear();
    }

    void clear() {
        lock.lock();
        try {
            removeAllMessagesLocked();
            for (HandlerThread ht : mThreads) {
                ht.quitLocked();
            }
            mThreads.clear();
        } finally {
            lock.unlock();
        }
    }

    boolean enqueueMessage(DmMessage msg, long when) {
        if (msg.target == null) {
            throw new IllegalArgumentException("Message must have a target.");
        }
        if (msg.isInUse()) {
            throw new IllegalStateException(msg + " This message is already in use.");
        }
        lock.lock();
//        Log.e("123", "enqueueMessage  ------- ");
        try {
//            if (mQuitting) {
//                msg.recycle();
//                return false;
//            }

            msg.markInUse();
            msg.when = when;
            DmMessage p = mMessages;
            if (p == null || when == 0 || when < p.when) {
                msg.next = p;
                mMessages = msg;
            } else {
                DmMessage prev;
                for (; ; ) {
                    prev = p;
                    p = p.next;
                    if (p == null || when < p.when) {
                        break;
                    }
                }
                msg.next = p;
                prev.next = msg;
            }

//            Log.e("123", "msg = " + msg);

            if (msg.target.isExecuting) {
                //如果handler中已经有消息正在处理,则直接退出
//                Log.e("123", "target.isExecuting ");
                return true;
            }

            if (dispatchThread == null) {
                dispatchThread = new MessageDispatchThread(lock);
                dispatchThread.start();
            }

            if (dispatchThread.waitWhen == 0 || msg.when < dispatchThread.waitWhen) {
//                Log.e("123", "enqueueMessage wakeup ");
                dispatchThread.wakeupLocked();
            }
        } finally {
//            Log.e("123", "enqueueMessage +++++++++ ");
            lock.unlock();
        }

        return true;
    }

    boolean hasMessages(DmHandler h, int what, Object object) {
        if (h == null) {
            return false;
        }
        lock.lock();
        try {
            DmMessage p = mMessages;
            while (p != null) {
                if (p.target == h && p.what == what && (object == null || p.obj == object)) {
                    return true;
                }
                p = p.next;
            }
            return false;
        } finally {
            lock.unlock();
        }
    }

    boolean hasMessages(DmHandler h, Runnable r, Object object) {
        if (h == null) {
            return false;
        }

        lock.lock();
        try {
            DmMessage p = mMessages;
            while (p != null) {
                if (p.target == h && p.callback == r && (object == null || p.obj == object)) {
                    return true;
                }
                p = p.next;
            }
            return false;
        } finally {
            lock.unlock();
        }
    }

    void removeMessages(DmHandler h, int what, Object object) {
        if (h == null) {
            return;
        }

        lock.lock();
        try {
            DmMessage p = mMessages;

            // Remove all messages at front.
            while (p != null && p.target == h && p.what == what
                    && (object == null || p.obj == object)) {
                DmMessage n = p.next;
                mMessages = n;
                p.recycle();
                p = n;
            }

            // Remove all messages after front.
            while (p != null) {
                DmMessage n = p.next;
                if (n != null) {
                    if (n.target == h && n.what == what
                            && (object == null || n.obj == object)) {
                        DmMessage nn = n.next;
                        n.recycle();
                        p.next = nn;
                        continue;
                    }
                }
                p = n;
            }
        } finally {
            lock.unlock();
        }
    }

    void removeMessages(DmHandler h, Runnable r, Object object) {
        if (h == null || r == null) {
            return;
        }

        lock.lock();
        try {
            DmMessage p = mMessages;

            // Remove all messages at front.
            while (p != null && p.target == h && p.callback == r
                    && (object == null || p.obj == object)) {
                DmMessage n = p.next;
                mMessages = n;
                p.recycle();
                p = n;
            }

            // Remove all messages after front.
            while (p != null) {
                DmMessage n = p.next;
                if (n != null) {
                    if (n.target == h && n.callback == r
                            && (object == null || n.obj == object)) {
                        DmMessage nn = n.next;
                        n.recycle();
                        p.next = nn;
                        continue;
                    }
                }
                p = n;
            }
        } finally {
            lock.unlock();
        }
    }

    void removeCallbacksAndMessages(DmHandler h, Object object) {
        if (h == null) {
            return;
        }

        lock.lock();
        try {
            DmMessage p = mMessages;

            // Remove all messages at front.
            while (p != null && p.target == h
                    && (object == null || p.obj == object)) {
                DmMessage n = p.next;
                mMessages = n;
                p.recycle();
                p = n;
            }

            // Remove all messages after front.
            while (p != null) {
                DmMessage n = p.next;
                if (n != null) {
                    if (n.target == h && (object == null || n.obj == object)) {
                        DmMessage nn = n.next;
                        n.recycle();
                        p.next = nn;
                        continue;
                    }
                }
                p = n;
            }
        } finally {
            lock.unlock();
        }
    }

    HandlerThread addNewThread() {
        HandlerThread ht = new HandlerThread(lock);
        mThreadCount++;
        mThreads.add(ht);
        ht.start();
        return ht;
    }

    void dispatch(MessageDispatchThread mdt) {
        lock.lock();
        try {
            for (; ; ) {
                // Try to retrieve the next message.  Return if found.
                final long now = SystemClock.uptimeMillis();
                DmMessage prevMsg = null;
                DmMessage msg = mMessages;
                if (msg != null && msg.target.isExecuting) {
                    do {
                        prevMsg = msg;
                        msg = msg.next;
                    }
                    while (msg != null && msg.target.isExecuting);
                }

                if (msg != null) {
                    if (now < msg.when) {
                        // Next message is not ready.  Set a timeout to wake up when it is ready.
//                        Log.e("123", "dispatchThread wait msg");
                        mdt.waitLocked(msg.when);
                    } else {
                        boolean del = false;
                        for (HandlerThread ht : mThreads) {
                            if (ht.setMessageLocked(msg)) {
//                                Log.e("123", "HandlerThread set msg " + mThreadCount);
                                del = true;
                                break;
                            }
                        }

                        if (!msg.target.isExecuting) {
                            if (mThreadCount < POOL_SIZE) {
                                HandlerThread ht = addNewThread();
                                ht.setMessageLocked(msg);
                                del = true;
//                                Log.e("123", "new HandlerThread set msg " + mThreadCount);
                            } else {
                                //等待任意一个HandlerThread完成后来唤醒,如果消息已经超时了10秒,强制启动线程来处理它
                                if (mThreadCount >= URGENT_SIZE) {
                                    mdt.sleepLocked();
                                } else {
                                    long timeOut = now - msg.when;
                                    if (timeOut >= MSG_TIMEOUT) {
                                        HandlerThread ht = addNewThread();
                                        ht.setMessageLocked(msg);
                                        del = true;
//                                        Log.e("123", "Urgent " + mThreadCount);
                                    } else {
                                        mdt.waitLocked(msg.when + MSG_TIMEOUT);
                                    }
                                }

                            }
                        }

                        if(del) {
                            if (prevMsg != null) {
                                prevMsg.next = msg.next;
                            } else {
                                mMessages = msg.next;
                            }

                            msg.next = null;
                        }
                    }
                } else {
                    mdt.sleepLocked();
                }
            }
        } finally {
            lock.unlock();
        }
    }

    void messageDispatchDone(DmMessage msg, HandlerThread ht) {
        lock.lock();
        try {
            ht.message = null;
            msg.target.isExecuting = false;
            dispatchThread.wakeupLocked();
        } finally {
            lock.unlock();
        }
        msg.recycle();
    }

    boolean threadIdleTimeout(HandlerThread ht) {
        if (mThreadCount > FIXED_SIZE) {
            mThreadCount--;
            mThreads.remove(ht);
            return true;
        }
        return false;
    }

    private void removeAllMessagesLocked() {
        DmMessage p = mMessages;
        while (p != null) {
            DmMessage n = p.next;
            p.recycle();
            p = n;
        }
        mMessages = null;
    }

    private class HandlerThread extends Thread {

        boolean quit;
        long idleTime;
        Condition condition;
        DmMessage message;
        Lock lock;

        HandlerThread(Lock inLock) {
            super("ght");
            lock = inLock;
            condition = lock.newCondition();
        }

        public void quitLocked() {
            quit = true;
            condition.signal();
        }

        public boolean setMessageLocked(DmMessage msg) {
            if (quit || message != null) {
                return false;
            }
            msg.target.isExecuting = true;
            message = msg;
            condition.signal();
            return true;
        }

        private DmMessage getMessage() {
            lock.lock();
            try {
                if (message == null) {
                    long wait = IDLE_TIME - idleTime;
                    if (wait <= 0) {
                        if (threadIdleTimeout(this)){
                            quit = true;
                            return null;
                        }
                        wait = IDLE_TIME;
                    }

                    long start = SystemClock.uptimeMillis();
                    try {
                        condition.await(wait, TimeUnit.MILLISECONDS);
                    } catch (InterruptedException e) {
                    }
                    idleTime += SystemClock.uptimeMillis() - start;
                    return message;
                } else {
                    return message;
                }
            }finally {
                lock.unlock();
            }
        }

        @Override
        public void run() {
            DmMessage msg;
            while (true) {
                msg = getMessage();
                if (quit) {
                    return;
                }
                if (msg != null) {
                    idleTime = 0;
                    msg.target.dispatchMessage(msg);
                    messageDispatchDone(msg, this);
                }
            }
        }
    }

    private class MessageDispatchThread extends Thread {

        Condition condition;
        long waitWhen;

        MessageDispatchThread(Lock inLock) {
            super("mdt");
            this.condition = inLock.newCondition();
        }

        @Override
        public void run() {
            dispatch(this);
        }

        void wakeupLocked() {
            condition.signal();
        }

        void sleepLocked() {
            waitWhen = ONE_YEAR + SystemClock.uptimeMillis();
            try {
                condition.await(ONE_YEAR, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
            }
        }

        void waitLocked(long when) {
            long wait = when - SystemClock.uptimeMillis();
            if (wait <= 0) {
                return;
            }
            waitWhen = when;
            try {
                condition.await(wait, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
            }
        }
    }

}
