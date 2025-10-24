package org.example.jpa;

import jakarta.persistence.EntityManager;

public class PersonMain {
    public static void main(String[] args) {
        create();
//        delete();
    }
    private static void delete(){
            EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
            em.getTransaction().begin();
            try{

                Person person = em.find(Person.class, 1L);
                em.remove(person);

                Passport passport = em.find(Passport.class, 1L);
                em.remove(passport);


                em.getTransaction().commit();
            }catch (Exception e){
                em.getTransaction().rollback();
            }
            finally{
                em.close();
            }

    }
    private static void find(){
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        try{


            em.getTransaction().commit();
        }catch (Exception e){
            em.getTransaction().rollback();
        }
        finally{
            em.close();
        }
    }
    private static void update(){
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        try{


            em.getTransaction().commit();
        }catch (Exception e){
            em.getTransaction().rollback();
        }
        finally{
            em.close();
        }
    }
    private static void create(){
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        try{
            Passport passport = new Passport();
            passport.setPassportNumber("c123243");  // Passport 엔티티 생성..   비영속

            Person person = new Person();
            person.setName("hong");

            //연관 관계 설정
            person.setPassport(passport);
            passport.setPerson(person);  //

            em.persist(person);
//            em.persist(passport);   // cascade = persist 설정이 되어있으므로..   person도 같이 저장.



            em.getTransaction().commit();
        }catch (Exception e){
            em.getTransaction().rollback();
        }
        finally{
            em.close();
        }
    }
}
