package at.htlkaindorf.singleton;

public class SingletonEager {
    private static final SingletonEager instance = new SingletonEager(); // Instanz wird sofort erstellt

    private SingletonEager() {}

    public static SingletonEager getInstance() {
        return instance;
    }
}
