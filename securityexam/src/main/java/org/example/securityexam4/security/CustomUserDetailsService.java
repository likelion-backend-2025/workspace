package org.example.securityexam4.security;

import lombok.RequiredArgsConstructor;
import org.example.securityexam4.domain.Role;
import org.example.securityexam4.domain.User;
import org.example.securityexam4.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException(username +  " 에 해당하는 사용자가 없습니다.");
        }

        UserBuilder userBuilder = org.springframework.security.core.userdetails.User.withUsername(username);
        userBuilder.password(user.getPassword());

        userBuilder.roles(
                user.getRoles()
                        .stream()
                        .map(Role::getName)//내부적으로  ROLE_  붙혀서 넣어줌!!
                        .toList()
                        .toArray(new String[0])
        );


        return userBuilder.build();
    }
}
