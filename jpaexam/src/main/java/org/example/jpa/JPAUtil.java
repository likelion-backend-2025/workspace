package org.example.jpa;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtil {
    // 싱글톤 인스턴스
    private static final EntityManagerFactory emfInstance =
            Persistence.createEntityManagerFactory("lionPU");

    // JVM 종료 시 자동 close
    static {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            if (emfInstance != null) {
                System.out.println("---- EntityManagerFactory 종료 ---");
                emfInstance.close();
            }
        }));
    }


    // 외부 인스턴스 생성 방지
    private JPAUtil() {}

    // EntityManagerFactory 반환
    public static EntityManagerFactory getEntityManagerFactory() {
        return emfInstance;
    }

}
