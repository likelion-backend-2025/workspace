package org.example.oauthexam.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "social_login_info")
@Getter @Setter
public class SocialLoginInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String provider;
    private String socialId;
    private LocalDateTime createdAt;
    private String uuid;

    public  SocialLoginInfo() {
        //소셜에 처음 로그인 할 때 우리는 추가 정보를 받을거예요. 추가 정보를 받는 페이지가 열린 후에..
        // 적절한 시간 이후에는 추가 작업을 하지 못하게 하려고..
        this.createdAt = LocalDateTime.now();  //소셜 로그인한 시간
        this.uuid = UUID.randomUUID().toString(); //UUID
    }
}
