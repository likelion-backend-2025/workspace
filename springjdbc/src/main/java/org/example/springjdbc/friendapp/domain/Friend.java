package org.example.springjdbc.friendapp.domain;


import lombok.*;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Friend {  //테이블명과 엔티티클래스명이 일치하다면 @Table 생략가능
    @Id
    private Long id;
    private String name;
    private String email;
}
