package org.example.jpa;

import jakarta.persistence.EntityManager;

public class EmployeeMain {
    public static void main(String[] args) {
create();
    }
    private static void create(){
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        try{
            Employee employee = new Employee("hong");

//            Project project = new Project("lion project");
            em.find(Project.class, 2L)

            employee.getProjects().add(project);
            project.getEmployees().add(employee);

            em.persist(employee);
            em.persist(project);


            em.getTransaction().commit();
        }catch (Exception e){
            em.getTransaction().rollback();
        }finally {
            em.close();
        }

    }

    private static void update(){
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        try{

        }catch (Exception e){
            em.getTransaction().rollback();
        }finally {
            em.close();
        }

    }
}
