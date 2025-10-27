package org.example.springdatajpa;

import static org.assertj.core.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional  //테스트가 끝난 후 롤백함!!
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() throws Exception {
        userRepository.deleteAll();

        userRepository.save(new User("carami","carami@gmail.com"));
        userRepository.save(new User("kang","kang@gmail.com"));
        userRepository.save(new User("kim","kim@gmail.com"));
        userRepository.save(new User("hong","hong@gmail.com"));
        userRepository.save(new User("lee","lee@gmail.com"));
    }

    @Test
    void 사용자_추가(){
        User user = new User("carami222","carami2222@gmail.com");
        User saveuser = userRepository.save(user);

//        assertThat(user.getId()).isNotNull();
//        assertThat(saveuser.getName()).isEqualTo("carami");
        System.out.println(saveuser);
    }

    @Test
    void 사용자_수정(){
        User user = new User("hong","hong@exam.com");
        User saveUser = userRepository.save(user);

        saveUser.setName("hhhhh");

        userRepository.save(saveUser);  // 수정도 save 를 사용한다.

        User updateUser = userRepository.findById(saveUser.getId()).get();
        System.out.println(updateUser);

        assertThat(updateUser.getName()).isEqualTo("hhhhh");
    }

    @Test
    void 사용자_삭제(){
        User user = new User("testUser","testUser@exam.com");
        userRepository.save(user);

        User findUser = userRepository.findById(user.getId()).get();

        userRepository.delete(user);
        assertThat(userRepository.findById(user.getId()).orElse(null)).isNull();
    }

    @Test
    void findByName(){
        userRepository.findByName("carami").
                forEach(user -> {System.out.println(user);});
    }

    @Test
    void findAll(){
        userRepository.findAll().forEach(user -> {System.out.println(user);});
    }
}
