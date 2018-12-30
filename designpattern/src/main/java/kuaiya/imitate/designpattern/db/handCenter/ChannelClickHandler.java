package kuaiya.imitate.designpattern.db.handCenter;

/**
 * Created by wangqiang on 2018/12/2.
 */

public class ChannelClickHandler implements IHandler {
    @Override
    public boolean processCommand(int id, EventParms parms, EventParms result) {
        boolean handled = true;

        switch (id) {
            case 1:
                break;
            default:
                handled = false;
                break;
        }

        return handled;
    }
}
