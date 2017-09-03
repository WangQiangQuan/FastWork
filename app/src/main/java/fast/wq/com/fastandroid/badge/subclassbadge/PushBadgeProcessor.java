package fast.wq.com.fastandroid.badge.subclassbadge;

import android.content.Context;

import fast.wq.com.fastandroid.badge.BadgeMessage;
import fast.wq.com.fastandroid.badge.BaseBadgeProcessor;

/**
 * Created by admin on 2017/9/3.
 */

public class PushBadgeProcessor extends BaseBadgeProcessor {
    public PushBadgeProcessor(Context context, int type) {
        super(context, type);
    }

    public void reloadData(){
        mMainHandler.removeCallbacksAndMessages(null);
        mMainHandler.post(new Runnable() {
            @Override
            public void run() {
                try {
                    BadgeMessage badgeMessage = new BadgeMessage();
                    badgeMessage.badgeType = 1;
                    badgeMessage.desc ="描述";
                    badgeMessage.lastOccurMills = System.currentTimeMillis();
                    badgeMessage.unreadMsgCount = 3;
                    notifyAllChanged(badgeMessage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
