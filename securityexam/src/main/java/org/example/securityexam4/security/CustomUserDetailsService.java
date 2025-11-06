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


@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
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
                        .map(Role::getName)
                        .toList()
                        .toArray(new String[0])
        );


        return userBuilder.build();
    }
}
