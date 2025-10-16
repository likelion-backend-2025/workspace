package org.example.springmvc.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.example.springmvc.domain.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

@Controller
@Slf4j
public class WelcomeController {
//    Logger log = LoggerFactory.getLogger(WelcomeController.class);

    @GetMapping("/")
    public String index(/*HttpServletRequest req,*/@RequestParam("name") String name, @RequestParam("message") String msg, Model model) {
        //종속된 관계를 가급적 피하려고 하는..  상속..
        //스프링 프레임워크는 서블릿 api 에 직접 접근하지 않아요.
        //필요한 값들을 사용할 수 있는 방법들을 제공해요.

        log.info("로그 출력 해볼까요??? ");
//        System.out.println(req.getRequestURI());
//        System.out.println(req.getParameter("message"));
        System.out.println(msg);
//
//        req.setAttribute("message", msg);
//
//        req.getAttribute("message");

        model.addAttribute("message", msg);
        model.addAttribute("name", name);

        return "index";
    }
    @GetMapping("/index")
    public String index2(@RequestParam(name="name",required = false,defaultValue = "guest") String name,
                         @RequestParam(name="message",required = false,defaultValue = "hi") String msg,
                         Model model) {

        model.addAttribute("name", name);
        model.addAttribute("message", msg);
        return "index";
    }

    @GetMapping("/index2")
    public ModelAndView index3(@RequestParam(name="name",required = false,defaultValue = "guest") String name,
                               @RequestParam(name="message",required = false,defaultValue = "hi") String msg){
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("name", name);
        modelAndView.addObject("message", msg);
        return modelAndView;
    }

    //https://v.daum.net/v/20251016105541847
    @GetMapping("/news/{newsid}")
    public String news(@PathVariable("newsid")String nid){
        System.out.println(nid);
        return "welcome";
    }


}
