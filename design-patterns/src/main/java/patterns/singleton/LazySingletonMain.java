package patterns.singleton;

public class LazySingletonMain {
    public static void main(String[] args) {
        LazySingleton lazySingleton;

        lazySingleton = LazySingleton.getInstance();

        lazySingleton.showMessage();

    }
}
