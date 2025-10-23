package org.example.jpa;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserDAO {
    private EntityManagerFactory emf;

    public  UserDAO() {
        this.emf = Persistence.createEntityManagerFactory("lionPU");
    }

    //입력


    //조회

    //수정

    //삭제


}
