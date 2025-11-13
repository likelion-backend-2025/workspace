package org.example.oauthexam.service;

import lombok.RequiredArgsConstructor;
import org.example.oauthexam.dto.SocialUserRequestDto;
import org.example.oauthexam.entity.Role;
import org.example.oauthexam.entity.User;
import org.example.oauthexam.repository.RoleRepository;
import org.example.oauthexam.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Optional<User> findByProviderAndSocialId(String provider, String socialId) {
        return userRepository.findByProviderAndSocialId(provider, socialId);
    }

    @Transactional
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Transactional
    public User saveUser(SocialUserRequestDto requestDto){
        //권한은 기본 USER 로 으로 부여
        Role userRole = roleRepository.findByName("USER")
                .orElseThrow(() -> new IllegalArgumentException("USER 권한이 없습니다. "));

        Set<Role> defaultRoles = Set.of(userRole);

        User socialEntity = requestDto.toSocialEntity(passwordEncoder, defaultRoles);
        return userRepository.save(socialEntity);
    }

}
