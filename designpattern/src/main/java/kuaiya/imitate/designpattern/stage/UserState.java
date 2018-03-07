package kuaiya.imitate.designpattern.stage;

/**
 * 用户的状态
 */

public interface UserState {
    /**
     * 转发
     */
    public void forward();
    /**
     * 评论
     */
    public void comment();
}
