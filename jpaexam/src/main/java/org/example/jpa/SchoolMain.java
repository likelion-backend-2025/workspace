package org.example.jpa;

import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SchoolMain {
    private static void find(){
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();

        //id가 1L 인 학교를 조회하고싶다.
        School school = entityManager.find(School.class, 1L);
        log.info("school Name ={}", school.getName());

        log.info("아이디가 1번인 학교 학생들...");
        for (Student student : school.getStudents()) {
            log.info("student Name ={}", student.getName());
        }

        //id가 1L인 학생을 조회하고 싶다.
        log.info("id가 1L인 학생");
        Student student = entityManager.find(Student.class, 1L);
        log.info("student Name ={}", student.getName());
        log.info("school Name ={} ", student.getSchool().getName());
        log.info("school id ={} ", student.getSchool().getId());
    }

    private static void create(){
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        try{
            //lion school 이라는 학교를 입력하고 싶어요.
            //학생도 3명 같이 생성하고 싶어요.
            School school = new School();
            school.setName("lion school");

            Student student = new Student("lion student1", school);

            school.getStudents().add(student);
            school.getStudents().add(new Student("lion student2", school));
            school.getStudents().add(new Student("lion student3", school));

            entityManager.persist(school);

            entityManager.getTransaction().commit();
        }finally {
            entityManager.close();
        }
    }

    private static void update(){
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        try{
            School school = entityManager.find(School.class, 4L);
            school.setName("lion high school");


            entityManager.getTransaction().commit();
        }finally {
            entityManager.close();
        }
    }


    private static void delete(){
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        try{

            School school = entityManager.find(School.class, 4L);
            entityManager.remove(school);


            entityManager.getTransaction().commit();
        }finally {
            entityManager.close();
        }
    }

    public static void main(String[] args) {
//        find();
//        create();
//        update();
        delete();
    }
}
