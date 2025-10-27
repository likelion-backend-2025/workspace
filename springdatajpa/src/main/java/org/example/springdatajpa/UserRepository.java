package org.example.springdatajpa;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {
    List<User> findByName(String name);
    List<User> findByEmail(String email);
    //like 검색  where name like %name% ;
    List<User> findByNameContaining(String name);
    //입력된 이름과,  이메일에 해당하는  user 를 찾아주세요.
    //where name= ? and email = ?
    List<User> findByNameAndEmail(String name, String email);

    //입력된 이름이거나,  이메일인 user  를 찾아주세요.
    List<User> findByNameOrEmail(String name, String email);

    


}
