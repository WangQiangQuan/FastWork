package kuaiya.imitate.designpattern.db.handCenter;

/**
 * 1 上行 IObserver 观察者
 * 2 下行 IProcessor 命令
 */

public interface IProcessor {
    boolean processCommand(int id, EventParms parms, EventParms result);
}
