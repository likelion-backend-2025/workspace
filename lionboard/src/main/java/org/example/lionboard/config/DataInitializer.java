package org.example.lionboard.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.lionboard.domain.Comment;
import org.example.lionboard.domain.Post;
import org.example.lionboard.domain.Role;
import org.example.lionboard.domain.User;
import org.example.lionboard.repository.CommentRepository;
import org.example.lionboard.repository.PostRepository;
import org.example.lionboard.repository.RoleRepository;
import org.example.lionboard.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class DataInitializer {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final PasswordEncoder passwordEncoder;

    @Bean
    @Transactional
    public CommandLineRunner initData() {
        return args -> {
            if (roleRepository.count() > 0) {
                log.info("데이터가 이미 존재합니다. 초기화를 건너뜁니다.");
                return;
            }

            log.info("초기 데이터 생성을 시작합니다.");

            // 1. 권한 데이터 생성
            Role userRole = roleRepository.save(new Role("USER"));
            Role adminRole = roleRepository.save(new Role("ADMIN"));
            log.info("권한 데이터 생성 완료");

            // 2. 사용자 데이터 생성
            User user1 = new User("User1", "user1", passwordEncoder.encode("1234"), "user1@example.com");
            user1.getRoles().add(userRole);
            user1 = userRepository.save(user1);

            User user2 = new User("User2", "user2", passwordEncoder.encode("1234"), "user2@example.com");
            user2.getRoles().add(userRole);
            user2 = userRepository.save(user2);

            User admin = new User("admin", "admin", passwordEncoder.encode("1234"), "admin@example.com");
            admin.getRoles().add(userRole);
            admin.getRoles().add(adminRole);
            admin = userRepository.save(admin);
            log.info("사용자 데이터 생성 완료");

            // 3. 게시글 데이터 생성
            Post post1 = new Post("Title 1", "Content of title 1", user1);
            post1 = postRepository.save(post1);

            Post post2 = new Post("Title 2", "Content of title 2", user1);
            post2 = postRepository.save(post2);
            log.info("게시글 데이터 생성 완료");

            // 4. 댓글 데이터 생성
            commentRepository.save(new Comment("첫 번째 댓글입니다!", post1, user1));
            commentRepository.save(new Comment("좋은 글 감사합니다.", post1, user2));
            commentRepository.save(new Comment("유익한 정보네요.", post1, user1));
            commentRepository.save(new Comment("잘 읽었습니다!", post2, user2));
            commentRepository.save(new Comment("도움이 되었어요.", post2, user1));
            log.info("댓글 데이터 생성 완료");

            log.info("초기 데이터 생성이 완료되었습니다.");
        };
    }
}