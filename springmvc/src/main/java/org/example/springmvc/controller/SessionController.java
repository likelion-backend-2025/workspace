package org.example.springmvc.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@SessionAttributes("visitCount")
public class SessionController {
    @ModelAttribute("visitCount")  //속성을 초기화 해주는 부분...
    public Integer initVisitCount() {
        System.out.println("initVisitCount");
        return 0;
    }

    @GetMapping("/visit")
    public String visit(@ModelAttribute(name = "visitCount") Integer visitCount,Model model) {

        visitCount++;
        model.addAttribute("visitCount", visitCount);   //sessionScope에 저장되어야함!!

        return "visit";
    }


    //상태정보로 유지하고싶은 값을 받아오는 화면
    @GetMapping("/sessionForm")
    public String sessionForm() {

        return "session_form";
    }

    @GetMapping("/addSession")
    public String addcookie(@RequestParam("sessionKey")String sessionKey,
                            @RequestParam("sessionValue")String sessionValue,
                            HttpSession session) {
        //사용자가보낸 쿠키 이름과 값을 꺼내서 쿠키를 생성하고,
        //생성된 쿠키를 반드시 다시 클라이언트 보내야함!!!
        System.out.println("session name is " + sessionKey);
        System.out.println("session value is " + sessionValue);

        //유지할 정보를 세션에 저장
        session.setAttribute(sessionKey, sessionValue);

        return "redirect:/sessionView";
    }

    @GetMapping("sessionView")
    public String cookieView(HttpSession session, Model model) {
        //세션이 가진 모든 값을 출력해 본다면??
        Map<String,Object> map = new HashMap<>();

        Enumeration<String> attributeNames = session.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            String attributeName = attributeNames.nextElement();
            Object attribute = session.getAttribute(attributeName);
            map.put(attributeName, attribute);
        }

        model.addAttribute("session", map);
        System.out.println(map.size());

        return "session_view";
    }

    //관리자전용페이지가 있다.
    //이 페이지를 어떻게 관리자에게만 보여주게 할 수 있을까요??
    @GetMapping("/adminPage_session")
    public String adminPage(@SessionAttribute(name="admin", required = false)Object admin ) {
        //세션에 admin이라는 속성이 있다면, 관리자라고 판단하고 admin_page로 이동

        if(admin != null){
            return "admin_page";
        }
        return "welcome";
    }

    //세션 삭제.
   @GetMapping("/sessionDel")
    public String cookieDel(HttpSession session) {

        //장바구니만 삭제한다.
//       session.removeAttribute("admin");

       //세션 전체를 삭제 (모든 속성들을 제거하고,  세션 무효화)
       session.invalidate();


        return "redirect:/sessionView";
    }
}
