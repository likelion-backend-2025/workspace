package org.example.oauthexam.entity;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "lion_users")
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true,  nullable = false,  length = 50)
    private String username;
    @Column(nullable = false,  length = 100)
    private String password;
    @Column(nullable = false,  length = 50)
    private String name;
    @Column(nullable = false,  length = 100)
    private String email;
    @Column(name = "registration_date", nullable = false, updatable = false)
    private LocalDateTime registrationDate;



    //추가된 컬럼들
    private String socialId;
    private String provider;



    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name="user_roles",
            joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    @PrePersist
    public void prePersist() {
        if(this.registrationDate == null){
            this.registrationDate = LocalDateTime.now();
        }
    }

    @Builder
    public User(Long id, String username, String password, String name,
                String email, LocalDateTime registrationDate, String socialId, String provider, Set<Role> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.registrationDate = registrationDate;
        this.socialId = socialId;
        this.provider = provider;
        this.roles = roles;
    }
}