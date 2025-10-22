package org.example.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaRun {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("lionPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        //트랜잭션 시작
        entityManager.getTransaction().begin();

        //입력
        User user = new User("kang","kang@nate.com");
        System.out.println("persist 전"+user);
//        user.setId(1L);
        //현재 user 라고 하는 엔티티는 아직 영속성 컨텍스트와는 관련 없다. (비영속)
        entityManager.persist(user);
        System.out.println("persist 후"+user);

        System.out.println("commit 실행 전!!!");
        entityManager.getTransaction().commit();
        System.out.println("commit 실행 후!!!");
    }
}
