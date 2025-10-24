package org.example.jpa;

import jakarta.persistence.EntityManager;

public class BookMain {
    private static void find(){
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
    }
    public static void main(String[] args) {
        find();
    }
}
