package patterns.singleton;

public class DoubleCheckedLockingSingleton {
    // volatile: 메모리 가시성 보장
    private static volatile DoubleCheckedLockingSingleton instance;

    private DoubleCheckedLockingSingleton() {
        System.out.println("DoubleCheckedLocking 인스턴스 생성");
    }

    public static DoubleCheckedLockingSingleton getInstance() {
        if (instance == null) {  // 첫 번째 체크 (lock 없이)
            synchronized (DoubleCheckedLockingSingleton.class) {
                if (instance == null) {  // 두 번째 체크 (lock 안에서)
                    instance = new DoubleCheckedLockingSingleton();
                }
            }
        }
        return instance;
    }

    public void showMessage() {
        System.out.println("Double-Checked Locking Singleton 작동 중");
    }
}