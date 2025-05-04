package at.htlkaindorf.singleton;

public class SingletonDoubleChecked {
    private static volatile SingletonDoubleChecked instance; // volatile sorgt dafür, dass variable immer aktuell ist

    private SingletonDoubleChecked() {

    }

    public static SingletonDoubleChecked getInstance() {
        if (instance == null) {
            synchronized (SingletonDoubleChecked.class) {
                if (instance == null) {
                    instance = new SingletonDoubleChecked();
                }
            }
        }
        return instance;
    }

    // Thread safe and performance optimized
}
