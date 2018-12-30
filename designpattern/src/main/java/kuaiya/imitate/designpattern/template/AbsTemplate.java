package kuaiya.imitate.designpattern.template;

/**
 * Created by wangqiang on 2018/12/30.
 */

public abstract class AbsTemplate {
    protected void stepOne() {
    }

    protected void stepTwo() {
    }

    protected void stepThree() {
    }

    public final void startUp() {
        stepOne();
        stepTwo();
        stepThree();
    }
}
