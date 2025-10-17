package org.example.springmvc.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SessionController {
    //상태정보로 유지하고싶은 값을 받아오는 화면
    @GetMapping("/sessionForm")
    public String sessionForm() {

        return "session_form";
    }

    @GetMapping("/addSession")
    public String addcookie(@RequestParam("sessionKey")String sessionKey,
                            @RequestParam("sessionValue")String sessionValue,
                            HttpServletResponse response) {
        //사용자가보낸 쿠키 이름과 값을 꺼내서 쿠키를 생성하고,
        //생성된 쿠키를 반드시 다시 클라이언트 보내야함!!!
        System.out.println("cookie name is " + sessionKey);
        System.out.println("cookie value is " + sessionValue);

        //유지할 정보를 세션에 저장
       

        return "redirect:/sessionView";
    }

    @GetMapping("sessionView")
    public String cookieView(HttpServletRequest req, Model model) {
        //클라언트가 가지고 있는 모든 쿠키를 화면에 출력함!!


        return "session_view";
    }

    //관리자전용페이지가 있다.
    //이 페이지를 어떻게 관리자에게만 보여주게 할 수 있을까요??
    @GetMapping("/adminPage_session")
    public String adminPage() {

        //이사람이 관리자야??
//        if(!admin.equals("false")) {
//            return "admin_page";
//        }else{
//            return "welcome";
//        }
        return "welcome";
    }

    //쿠키삭제
    //브라우저가(클라이언트) 같은 이름의 쿠키를 가질 수 없다.. 같은 이름의 쿠키가 전송되어오면 기존 쿠키와 교체한다!!
    @GetMapping("/sessionDel")
    public String cookieDel(HttpServletResponse response) {

        return "redirect:/sessionView";
    }
}
