package at.htlkaindorf.builder;

public class User {

    private final String name;
    private final int age;
    private final String address;
    private final String phone;

    public User (Builder builder) {
        this.name = builder.name;
        this.age = builder.age;
        this.address = builder.address;
        this.phone = builder.phone;
    }


    public static class Builder {
        private String name;
        private int age;
        private String address;
        private String phone;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder age(int age) {
            this.age = age;
            return this;
        }

        public Builder address(String address) {
            this.address = address;
            return this;
        }

        public Builder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }

    public static void main(String[] args) {
        User user = new Builder().name("Johannes").age(31).build();
    }

}
