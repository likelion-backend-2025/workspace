package org.example.securityexam3;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @GetMapping
    public String list(){
        return "admin list";
    }

    @GetMapping("/super")
    public String superAdmin(){
        return "superAdmin";
    }
}
