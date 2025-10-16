package org.example.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("/hi")
    public String hello(){
        return "welcome";
    }

    @GetMapping("/hello")
    public String hello2(){
        return "hello";
    }

    @ResponseBody
    @GetMapping("/rest")
    public String hello3(){
        return "hello rest!!! ";
    }
}
