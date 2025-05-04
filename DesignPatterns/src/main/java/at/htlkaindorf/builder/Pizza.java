package at.htlkaindorf.builder;

public class Pizza {

    // Step builder

    private final String dough;
    private final String sauce;
    private final String topping;

    private Pizza(Builder builder) {
        this.dough = builder.dough;
        this.sauce = builder.sauce;
        this.topping = builder.topping;
    }

    public interface DoughStep {
        SauceStep dough(String dough);
    }

    public interface SauceStep {
        ToppingStep sauce(String sauce);
    }

    public interface ToppingStep {
        BuildStep topping(String topping);
    }

    public interface BuildStep {
        Pizza build();
    }

    public static class Builder implements DoughStep, SauceStep, ToppingStep, BuildStep {
        private String dough;
        private String sauce;
        private String topping;

        public Pizza build() {
            return new Pizza(this);
        }

        public static DoughStep builder() {
            return new Builder();
        }

        @Override
        public SauceStep dough(String dough) {
            this.dough = dough;
            return this;
        }

        @Override
        public ToppingStep sauce(String sauce) {
            this.sauce = sauce;
            return this;
        }

        @Override
        public BuildStep topping(String topping) {
            this.topping = topping;
            return this;
        }
    }

    public static void main(String[] args) {
        Pizza pizza = Builder.builder().dough("asdf").sauce("asdf").topping("adsf").build();
    }

}
