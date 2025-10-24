package org.example.jpa;

import jakarta.persistence.EntityManager;

public class PersonMain {
    public static void main(String[] args) {
        create();
    }
    private static void create(){
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        try{
            Passport passport = new Passport();
            passport.setPassportNumber("A123243");  // Passport 엔티티 생성..   비영속

            Person person = new Person();
            person.setName("John");

            //연관 관계 설정
            person.setPassport(passport);
            passport.setPerson(person);  //

            em.persist(person);



            em.getTransaction().commit();
        }catch (Exception e){
            em.getTransaction().rollback();
        }
        finally{
            em.close();
        }
    }
}
