package org.example.securityexam4.service;

import lombok.RequiredArgsConstructor;
import org.example.securityexam4.domain.Role;
import org.example.securityexam4.domain.User;
import org.example.securityexam4.dto.UserRegisterDTO;
import org.example.securityexam4.repository.RoleRepository;
import org.example.securityexam4.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    //username에 해당하는 사용자가 있는지 체크.
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }


    //회원가입
    @Transactional
    public User registerUser(UserRegisterDTO registerDTO) {
        //DTO에 가져온 값을 엔티티에 옮겨 담을 필요가 있다.
        User user = new User();
        user.setUsername(registerDTO.getUsername());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));  //패스워드는 반드시 인코딩 되어야함!!!
        user.setName(registerDTO.getName());
        user.setEmail(registerDTO.getEmail());

        Set<Role> roles = new HashSet<>();

        //컨트롤러에서 사용자가 권한을 하나도 선택하지 않았다면,  디폴트로 USER 권한을 부여하고,
        if (registerDTO.getRoles() == null || registerDTO.getRoles().isEmpty()) {
            Role userRole = roleRepository.findByName("USER")
                    .orElseThrow(() -> new RuntimeException("USER 권한을 찾을 수 없습니다. "));
            roles.add(userRole);
        }else {
            //권한을 선택했다면, 선택한 권한들..로 회원 가입을 하게 할거예요.
            for(String roleName : registerDTO.getRoles()) {
                Role role = roleRepository.findByName(roleName)
                        .orElseThrow(() -> new RuntimeException(roleName + " 권한을 찾을 수 없습니다."));
                roles.add(role);
            }

        }

        user.setRoles(roles);

        return userRepository.save(user);
    }

}
