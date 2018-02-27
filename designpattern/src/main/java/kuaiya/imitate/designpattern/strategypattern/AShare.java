package kuaiya.imitate.designpattern.strategypattern;

/**
 * Created by admin on 2018/2/27.
 */

public abstract class AShare {
    int type;
    public AShare(int type) {
        this.type = type;
    }
    public abstract void doShare(ShareListener listener);
}
