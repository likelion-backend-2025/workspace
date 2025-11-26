package patterns.singleton;

public class LazySingleton {
    private static LazySingleton instance;

    private LazySingleton() {
        System.out.println("LazySingleton 인스턴스 생성");
    }

    // synchronized로 멀티스레드 안전성 확보
    public static synchronized LazySingleton getInstance() {
        if (instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }

    public void showMessage() {
        System.out.println("Lazy Singleton 작동 중");
    }
}