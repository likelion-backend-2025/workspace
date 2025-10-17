package org.example.springmvc.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.example.springmvc.domain.User;
import org.example.springmvc.domain.UserForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping("/abc")
    public String abc(HttpServletRequest request) {
        request.setAttribute("name","kang");
        return "forward:/user/register";
    }





    //user정보입력폼
    @GetMapping("/register")
    public String registerForm(Model model,HttpServletRequest request) {
        model.addAttribute("user", new User());
        System.out.println(request.getAttribute("name"));

        if(true)
            throw new RuntimeException("테스트용 예외 발생");

        return "registerForm";
    }



    //회원가입해줘요.
    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("user") User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "registerForm"; //오류가 발생했다면... 여기로 보내주세요.
        }

        return "welcome";
    }




}
