package org.example.swaggerexam.service;

import lombok.RequiredArgsConstructor;
import org.example.swaggerexam.entity.User;
import org.example.swaggerexam.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    //회원가입
    @Transactional
    public String register(String email, String password) {
        //매개변수로 들어온 이메일이 이미 존재 하는지 체크

        if(userRepository.findByEmail(email).isPresent()) {
            //존재하면  "이미 존재하는 아이디입니다" 리턴
            return "이미 존재하는 아이디입니다";
        }

        //존재하지 않으면 DB에 저장하고 "회원가입 성공"  리턴
        //엔티티 생성해서, 엔티티에 이메일 패스워드 값을 넣고 저장
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        userRepository.save(user);

        return "회원가입 성공";
    }
}
