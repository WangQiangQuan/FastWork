package kuaiya.imitate.designpattern;

/**
 * plugins 中搜索builder
 */

public class User {
    String name;
    String age;

    private User(Builder builder) {
        name = builder.name;
        age = builder.age;
    }


    public static final class Builder {
        private String name;
        private String age;

        public Builder() {
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder age(String val) {
            age = val;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
