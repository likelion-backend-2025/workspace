package org.example.springjdbc.jdbc01;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

@SpringBootApplication
public class JDBCApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(JDBCApplication.class, args);
    }

//    JDBC 코드를 직접 만드는 것이 아니고, SpringJDBC 가 제공하는
//    JDBCTempleate 를 이용하면 반복되는 코드는 알아서 만들어 줍니다.

    //필드 주입을 통해서 JdbcTemplate를 얻어옴.
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UserDao userDao;

    @Override
    public void run(String... args) throws Exception {
//        userDao.insertUser(new User("hong","hong@hong.com"));

        List<User> allUsers = userDao.findAllUsers();

        for (User user : allUsers) {
            System.out.println(user);
        }
        System.out.println(allUsers.size());

        //1. 입력 (insert)
//        String sql = "insert into users(name,email)values(?,?)";
//        int count = jdbcTemplate.update(sql, "kang", "kang@kang.com");
//        System.out.println(count+"건 입력성공!!");

        //2. 조회 (select)
//        String selectSql = "select * from users";
//        List<User> userList = jdbcTemplate.query(selectSql, new BeanPropertyRowMapper<>(User.class));
//
//        for (User user : userList) {
//            System.out.println(user);
//        }

    }
}
