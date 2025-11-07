
-- 권한데이터
INSERT INTO roles(name) VALUES ("USER"),("ADMIN");



-- 사용자 데이터
INSERT INTO Users (name, login_id, password, email)
VALUES ('User1', 'user1', '1234', 'user1@example.com'),
       ('User2', 'user2', '1234', 'user2@example.com'),
       ('admin', 'admin', '1234', 'admin@example.com');


-- 사용자 별 권한 데이터
-- User1에게 USER 권한 부여 (user_id=1, role_id=1)
INSERT INTO user_roles (user_id, role_id) VALUES (1, 1);
-- User2에게 USER 권한 부여 (user_id=2, role_id=1)

INSERT INTO user_roles (user_id, role_id) VALUES (2, 1);
-- admin에게 ADMIN 권한 부여 (user_id=3, role_id=2)

INSERT INTO user_roles (user_id, role_id) VALUES (3, 2);

-- admin에게 USER 권한도 추가로 부여
INSERT INTO user_roles (user_id, role_id) VALUES (3, 1);

-- 게시글 데이터
INSERT INTO Posts (title, content, user_id)
VALUES ('Title 1', 'Content of title 1', 1),
       ('Title 2', 'Content of title 2', 1);


-- 댓글 데이터
-- 첫 번째 글의 댓글들
INSERT INTO Comments (content, post_id, user_id)
VALUES ('첫 번째 댓글입니다!', 1, 1),
       ('좋은 글 감사합니다.', 1, 2),
       ('유익한 정보네요.', 1, 1);

-- 두 번째 글의 댓글들
INSERT INTO Comments (content, post_id, user_id)
VALUES ('잘 읽었습니다!', 2, 2),
       ('도움이 되었어요.', 2, 1);

