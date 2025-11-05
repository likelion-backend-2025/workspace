package org.example.securityexam3;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class HomeService {
    public void userLog(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal instanceof UserDetails){
            UserDetails user = (UserDetails) principal;
            log.info("현재 로그인 한 사용 자 : "+ user.getUsername());
        }else{
            log.info("현재 로그인 한 사용자 ::" + principal.toString());
        }
    }
}
