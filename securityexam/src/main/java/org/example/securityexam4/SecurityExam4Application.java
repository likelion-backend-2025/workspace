package org.example.securityexam4;

import lombok.extern.slf4j.Slf4j;
import org.example.securityexam4.domain.Role;
import org.example.securityexam4.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.List;


@SpringBootApplication
@Slf4j
public class SecurityExam4Application {
    public static void main(String[] args) {
        SpringApplication.run(SecurityExam4Application.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(RoleRepository roleRepository) {
        return args -> {
          //데이터베이스에 Role 테이블에 데이터가 있으면 실행하지 않고,  하나도 없다면
          //USER와 ADMIN 을 추가하려고 해요.
          if(roleRepository.count()==0){
              Role userRole = new Role();
              userRole.setName("USER");

              Role adminRole = new Role();
              adminRole.setName("ADMIN");

              roleRepository.saveAll(List.of(userRole,adminRole));
              log.info("USER, ADMIN 권한이 추가 되었습니다.");
          }else{
              log.info("권한 정보가 이미 존재합니다.");
          }

        };
    }
}
