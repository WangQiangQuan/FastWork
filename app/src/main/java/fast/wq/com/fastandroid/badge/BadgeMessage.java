package fast.wq.com.fastandroid.badge;

import android.content.Intent;

/**
 * Created by admin on 2017/8/30.
 */

public class BadgeMessage {

    public int badgeType;
    public long lastOccurMills;
    public int unreadMsgCount;
    public int prioprity;
    public String desc;
    public Intent intent;

    public int extraCount;

    public BadgeMessage(BadgeMessage message){
        badgeType = message.badgeType;
        lastOccurMills = message.lastOccurMills;
        unreadMsgCount = message.unreadMsgCount;
        prioprity = message.prioprity;
        desc = new String(message.desc);
        intent = new Intent(intent);
    }

    public BadgeMessage(){

    }

    @Override
    public String toString() {
        return "badgeType:"+badgeType+",unreadMsgCount:"+unreadMsgCount+",extraCount:"+extraCount;
    }
}
