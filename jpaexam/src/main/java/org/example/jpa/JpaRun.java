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
        User user = new User("홍길동","hong@nate.com");
//        System.out.println("persist 전"+user);
//        user.setId(3L);
//        //현재 user 라고 하는 엔티티는 아직 영속성 컨텍스트와는 관련 없다. (비영속)
        entityManager.persist(user);  // 이 때 user 엔티티는 영속 상태가 됨.
//        System.out.println("persist 후"+user);

        //조회
//        User findUser = entityManager.find(User.class, 3L);
//
//        User findUser2 = entityManager.find(User.class, 3L);
//
//        User findUser3 = entityManager.find(User.class, 3L);
//
//        System.out.println(findUser);
//
//        if(findUser2 == findUser){
//            System.out.println("같다!!");
//        }
//
//        if(findUser2 == findUser3){
//            System.out.println("같다!!");
//        }

        //수정
//        User updateUser = entityManager.find(User.class, 1L);  //updateUser는 영속vs비영속
//
//        updateUser.setName("kang");
//
//
//        updateUser.setName("아무개");

        //삭제
//        User delUser = entityManager.find(User.class, 1L);
//        entityManager.remove(delUser);


        System.out.println("commit 실행 전!!!");
        entityManager.getTransaction().commit();
        System.out.println("commit 실행 후!!!");
    }
}
