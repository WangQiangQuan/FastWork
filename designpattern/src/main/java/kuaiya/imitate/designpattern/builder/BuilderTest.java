package kuaiya.imitate.designpattern.builder;

import kuaiya.imitate.designpattern.User;

/**
 * Created by admin on 2018/3/27.
 */

public class BuilderTest {
    public void get(){
        User.Builder builder = new User.Builder();
        builder.age("1");
        User mUser= builder.build();
    }
}
