package org.example.securityexam4.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.securityexam4.dto.UserRegisterDTO;
import org.example.securityexam4.repository.UserRepository;
import org.example.securityexam4.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
@Slf4j
public class UserController {
    private final UserService userService;

    @GetMapping("/welcome")
    public String welcome() {
        return "exam4/welcome";
    }

    @GetMapping("/regForm")
    public String regForm() {
        return "exam4/users/signup";
    }

    @PostMapping("/userreg")
    public String userreg(@ModelAttribute UserRegisterDTO userRegisterDTO) {
        System.out.println("user reg!!!  ");
        //사용자가 입력한 username이 이미 시스템에 존재하는지 체크
        if(userService.existsByUsername(userRegisterDTO.getUsername())) {
            log.info("이미 사용중인 아이디 :: "+ userRegisterDTO.getUsername());
            return "redirect:/user/regForm";
        }

        userService.registerUser(userRegisterDTO);
        return "redirect:/user/welcome";
    }



    //로그인 폼 요청
    @GetMapping("/loginform")
    public String loginForm() {
        return "exam4/users/loginform";
    }
}
