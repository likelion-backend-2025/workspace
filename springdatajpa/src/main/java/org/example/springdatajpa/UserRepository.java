package org.example.springdatajpa;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {

    //쿼리메서드를 이용해 보고 있어요^^   크게 어렵지는 않으시죠??

    List<User> findByName(String name);
    List<User> findByEmail(String email);
    //like 검색  where name like %name% ;
    List<User> findByNameContaining(String name);
    //입력된 이름과,  이메일에 해당하는  user 를 찾아주세요.
    //where name= ? and email = ?
    List<User> findByNameAndEmail(String name, String email);

    //입력된 이름이거나,  이메일인 user  를 찾아주세요.
    List<User> findByNameOrEmail(String name, String email);


    @Query("SELECT u FROM User u WHERE u.name=:name")
    List<User> selectUser(@Param("name")String name);

    @Query("SELECT u FROM User u WHERE u.name LIKE %:name%")
    List<User> selectUserByName(@Param("name")String name);

    @Transactional
    @Modifying
    @Query("DELETE FROM User u WHERE u.email=:email")
    int deleteUserByEmail(@Param("email")String email);

    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.email = :email WHERE u.id = :id")
    int updateUserByEmail(@Param("id")Long id,@Param("email")String email);


    @Query(nativeQuery = true, value = "SELECT * FROM jpa_users WHERE email Like %:email% ")
    List<User> selectUserByEmail(@Param("email")String email, Pageable pageable);



}
