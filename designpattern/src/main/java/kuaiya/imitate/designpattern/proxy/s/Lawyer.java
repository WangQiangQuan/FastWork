package kuaiya.imitate.designpattern.proxy.s;

/**
 * 代理律师
 */

public class Lawyer implements ILawsuit {

    ILawsuit mLawsit;

    public Lawyer(ILawsuit mLawsit) {
        this.mLawsit = mLawsit;
    }

    @Override
    public void submit() {
        mLawsit.submit();
    }

    @Override
    public void burden() {
        mLawsit.burden();
    }

    @Override
    public void defend() {
        mLawsit.defend();
    }

    @Override
    public void finish() {
        mLawsit.finish();
    }
}
