package org.example.springjdbc.springdatajdbc;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> ,UserDao{
    User findByName(String name); //select * from users where name=?
    Optional<User> findByEmail(String email); //select * from users where email=?
    Optional<User> findByNameAndEmail(String name, String email);//SELECT * FROM USERS WHERE ID = ? AND EMAIL = ?
}
