package org.example.springmvc.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CookieController {
    //상태정보로 유지하고싶은 값을 받아오는 화면
    @GetMapping("/cookieForm")
    public String cookieForm() {

        return "cookie_form";
    }

    @GetMapping("/addcookie")
    public String addcookie(@RequestParam("cookieName")String cookieName,
                            @RequestParam("cookieValue")String cookieValue,
                            HttpServletResponse response) {
        //사용자가보낸 쿠키 이름과 값을 꺼내서 쿠키를 생성하고,
        //생성된 쿠키를 반드시 다시 클라이언트 보내야함!!!
        System.out.println("cookie name is " + cookieName);
        System.out.println("cookie value is " + cookieValue);

        //유지할 정보를 쿠키객체로 생성
        Cookie cookie = new Cookie(cookieName, cookieValue);
        cookie.setPath("/");
        cookie.setMaxAge(60*60*24);   //쿠키 유지시간.

        response.addCookie(cookie);

        return "redirect:/cookieView";
    }

    @GetMapping("cookieView")
    public String cookieView(HttpServletRequest req) {
        //클라언트가 가지고 있는 모든 쿠키를 화면에 출력함!!
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                System.out.print(cookie.getName() +":::::"+cookie.getValue());
            }
            System.out.println();
        }


        return "cookie_view";
    }
}
