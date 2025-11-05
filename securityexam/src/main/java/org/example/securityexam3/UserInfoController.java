package org.example.securityexam3;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserInfoController {
    @GetMapping("/userinfo")
    public String userinfo(){
        return "exam3/user-info";
    }
}
