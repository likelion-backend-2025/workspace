package org.example.springjdbc.springdatajdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserDaoImpl implements UserDao {
    private final JdbcTemplate jdbcTemplate;

    //직접 구현!!

    @Override
    public Optional<User> abcUser(String email) {
        System.out.println("내가 직접 구현한 부분이 실행됨!!!");
        System.out.println("많이 복잡한 쿼리를 이용해서 내가 원하는 정보를 얻어야 한다면???? ");
        //직접 jdbcTemplate를 이용해서 내가 사용할 복잡한 쿼리를 실행해서 결과를 얻어옴!!!
        return Optional.empty();
    }
}
