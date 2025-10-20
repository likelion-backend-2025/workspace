package org.example.springjdbc.springdatajdbc;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    
}
