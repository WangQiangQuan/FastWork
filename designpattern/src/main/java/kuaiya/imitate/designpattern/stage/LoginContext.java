package kuaiya.imitate.designpattern.stage;

/**
 * 用户接口和状态的管理类
 */

public class LoginContext implements UserState{
    public UserState getmCurrentState() {
        return mCurrentState;
    }

    public void setmCurrentState(UserState mCurrentState) {
        this.mCurrentState = mCurrentState;
    }

    UserState mCurrentState = new LoginOutState();

    static LoginContext mInst = new LoginContext();

    private LoginContext(){
    }


    @Override
    public void forward() {
        mCurrentState.forward();
    }

    @Override
    public void comment() {
        mCurrentState.comment();
    }
}
