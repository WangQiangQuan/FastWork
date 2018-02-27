package kuaiya.imitate.designpattern.strategypattern;

/**
 * Created by admin on 2018/2/27.
 */

public interface ShareListener {
    int STATE_SUCC = 0;
    int STATE_FAIL = 1;
    void onCallback(int state, String msg);
}
