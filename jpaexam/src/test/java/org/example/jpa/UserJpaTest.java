package org.example.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserJpaTest {
    private static EntityManagerFactory emf;
    private EntityManager em;
    private EntityTransaction tx;


    //테스트가 실행될때마다 EntityManagerFactory 가 매번 생성될 필요가 있을까요?
    @BeforeAll
    public static void setUpClass() {
        emf = Persistence.createEntityManagerFactory("lionPU");
    }
    @AfterAll
    public static void tearDownClass() {
        if(emf != null) {
            emf.close();
        }
    }


//    EntityManager는 전체 테스트에서 하나만 생성되면 될까요??  테스트마다 생성되어야할까요?
    @BeforeEach
    public void setUp() {
        em = emf.createEntityManager();
        tx = em.getTransaction();
        tx.begin();
    }
    @AfterEach
    public void tearDown() {
        if(tx != null && tx.isActive()) {
            tx.rollback();
        }
        if(em != null) {
            em.close();
        }
    }

    @Test
    @DisplayName("Insert test ::  성공하면 id 값이 존재함.")
    void insertUser(){
        User user = new User("admin","admin@admin.com");
        em.persist(user);
        tx.commit();

        assertNotNull(user.getId(),"입력이 성공적으로 일어나면id는 null이 아니어야 합니다.");
    }

    @Test
    void findId(){
        //아디가 몇 번인 것을 테스트해야지 테스트가 올바로 진행될까요??
        User user = new User("caramiUser", "carami@gmail.com");
        em.persist(user);
        tx.commit();  // 실제 DB에 저장 되겠죠??

        tx = em.getTransaction();
        tx.begin();

        User findUser = em.find(User.class, user.getId());
        User findUser2 = em.find(User.class, user.getId());

        assertSame(findUser,findUser2);
    }

}
