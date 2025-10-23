package org.example.jpa;

import jakarta.persistence.EntityManager;

public class SchoolMain {
    private static void find(){
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();

        //id가 1L 인 학교를 조회하고싶다.


        //id가 1L인 학생을 조회하고 싶다.
    }

    public static void main(String[] args) {
        find();
    }
}
