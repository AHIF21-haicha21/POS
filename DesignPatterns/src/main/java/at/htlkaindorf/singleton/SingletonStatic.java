package at.htlkaindorf.singleton;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class SingletonStatic implements Serializable {

    private SingletonStatic() {

    }

    private static class Holder {
        private static final SingletonStatic INSTANCE = new SingletonStatic();
    }

    public static SingletonStatic getInstance() {
        return Holder.INSTANCE;
    }

    @Serial
    private Object readResolve() {
        return Holder.INSTANCE;
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("file.txt"));
        SingletonStatic newInstance = (SingletonStatic) in.readObject();

        Constructor<SingletonStatic> constructor = SingletonStatic.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        SingletonStatic myInstance = constructor.newInstance();

    }

}
