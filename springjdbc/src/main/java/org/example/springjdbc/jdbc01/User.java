package org.example.springjdbc.jdbc01;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    private String username;
    private String email;
    private LocalDateTime createdAt;

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }
}
