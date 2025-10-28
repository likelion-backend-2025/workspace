package org.example.springdatajpa3;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> , UserCustomRepository{
}
