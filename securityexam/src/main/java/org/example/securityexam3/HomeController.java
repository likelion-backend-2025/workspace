package org.example.securityexam3;

import lombok.RequiredArgsConstructor;
import org.example.basicsecurity.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HomeController {
    private final HomeService homeService;
    @GetMapping("/hello")
    public String hello(){
        return "Hello World!";
    }

    @GetMapping("/info")
    public String info(){
        String message = "";
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null || !authentication.isAuthenticated()){
            message = "로그인 한 사용자가 없습니다.";
        }
        Object principal = authentication.getPrincipal();
        if(principal instanceof UserDetails){
            UserDetails user = (UserDetails) principal;
            message = "현재 로그인 한 사용자는 :: "+user.getUsername();
        }else{
            message = "현재 로그인 한 사용자는 :: "+principal.toString();
        }
        return message;
    }

    @GetMapping("/info_aa")
    public String info_aa(@AuthenticationPrincipal UserDetails user){
        return "로그인한 사용자 ::"+user.getUsername();
    }

    @GetMapping("/log_test")
    public String log_test(){
        homeService.userLog();

        return "log_test";

    }


}
