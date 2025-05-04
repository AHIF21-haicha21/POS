package at.htlkaindorf.singleton;

public enum SingletonEnum {
    INSTANCE;

    public void doSomething() {
        System.out.println("Something");
    }
}