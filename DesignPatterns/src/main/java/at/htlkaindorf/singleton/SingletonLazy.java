package at.htlkaindorf.singleton;

public class SingletonLazy {

    private static SingletonLazy instance;

    private SingletonLazy() {

    }

    public static SingletonLazy getInstance() {
        if (instance == null) {
            instance = new SingletonLazy();
        }
        return instance;
    }

    // Problem: NOT THREAD SAFE

}
