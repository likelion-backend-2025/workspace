package org.example.jwtexam.security;

import org.example.jwtexam.domain.Role;
import org.example.jwtexam.domain.User;
import org.example.jwtexam.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomUserDetailsServiceTest {
    @Test
    @DisplayName("존재하지 않는 사용자 아이디로 메서드를 실행했을 때 UsernameNotFoundException 발생 ")
    void loadUserByUsername_notFound(){
        //given
        UserRepository userRepository = mock(UserRepository.class);// 가짜객체를 넣어줌.
        CustomUserDetailsService customUserDetailsService
                = new CustomUserDetailsService(userRepository);

        when(userRepository.findByUsername("noname")).thenReturn(Optional.empty());

        //when
//        CustomUserDetails userDetails = (CustomUserDetails)customUserDetailsService.loadUserByUsername("noname");

        assertThrows(UsernameNotFoundException.class,
                ()->customUserDetailsService.loadUserByUsername("noname"));

    }

    @Test
    @DisplayName("존재하는 username 으로 메서드를 실행했을 때 CustomUserDetails 로 잘 반환 되는지 확인")
    void loadUserByUsername() {
        //given
        UserRepository userRepository = mock(UserRepository.class);// 가짜객체를 넣어줌.
        CustomUserDetailsService customUserDetailsService
                = new CustomUserDetailsService(userRepository);

        User user = new User();
        user.setUsername("carami");
        user.setPassword("1234");
        user.setName("강경미");

        Role roleUser = new Role();
        roleUser.setId(1L);
        roleUser.setName("USER");

        Role roleAdmin = new Role();
        roleAdmin.setId(2L);
        roleAdmin.setName("ADMIN");

        user.setRoles(Set.of(roleUser,roleAdmin));

        when(userRepository.findByUsername("carami")).thenReturn(Optional.of(user));

        //when
        CustomUserDetails userDetails = (CustomUserDetails)customUserDetailsService.loadUserByUsername("carami");

        //then
        assertEquals("carami", userDetails.getUsername());
        assertEquals("1234", userDetails.getPassword());
        assertEquals("강경미",userDetails.getName());
        assertTrue(userDetails.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN")));


        verify(userRepository,times(1)).findByUsername("carami");
    }
}