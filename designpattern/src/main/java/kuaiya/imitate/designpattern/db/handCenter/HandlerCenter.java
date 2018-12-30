package kuaiya.imitate.designpattern.db.handCenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangqiang on 2018/12/2.
 * 注册分发事件
 */

public class HandlerCenter implements IProcessor {

    private List<IHandler> mHandlers = new ArrayList<>();

    @Override
    public boolean processCommand(int id, EventParms parms, EventParms result) {

        for (IHandler procesor : mHandlers) {
            boolean intercept = procesor.processCommand(id, parms, result);
            if (intercept) {
                return true;
            }
        }
        return false;
    }

    public void addHandler(IHandler procesor) {
        synchronized (this) {
            mHandlers.add(procesor);
        }
    }
}
