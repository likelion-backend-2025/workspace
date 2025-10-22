package org.example.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class ProductRun {
    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory
                = Persistence.createEntityManagerFactory("lionPU");

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        Product product1 = Product.builder()
                .name("사과")
                .price(1000)
                .build();

        Product product2 = Product.builder()
                .name("배")
                .price(2000)
                .build();

        entityManager.persist(product1);
        entityManager.persist(product2);

        Product selectProduct = entityManager.find(Product.class, 2L);
        System.out.println("selectProduct = " + selectProduct);
        //product에 데이터를 입력/조회/수정/삭제 를 테스트 해보세요.
    }
}
