

DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS roles;
DROP TABLE IF EXISTS comments;
DROP TABLE IF EXISTS posts;
DROP TABLE IF EXISTS users;


CREATE TABLE roles (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE Users (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       name VARCHAR(100) NOT NULL,
                       login_id VARCHAR(100) UNIQUE NOT NULL,
                       password VARCHAR(100) NOT NULL,
                       email VARCHAR(100) NOT NULL,
                       joined_date DATETIME DEFAULT CURRENT_TIMESTAMP
);


CREATE TABLE user_roles (
                            user_id BIGINT NOT NULL,
                            role_id BIGINT NOT NULL,
                            PRIMARY KEY (user_id, role_id),
                            FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
                            FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE CASCADE
);

CREATE TABLE Posts (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       title VARCHAR(255) NOT NULL,
                       content TEXT NOT NULL,
                       posted_date DATETIME DEFAULT CURRENT_TIMESTAMP,
                       user_id BIGINT NOT NULL,
                       FOREIGN KEY (user_id) REFERENCES Users(id)
);


CREATE TABLE Comments (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          content TEXT NOT NULL,
                          del_yn BOOLEAN DEFAULT FALSE,
                          regdate DATETIME DEFAULT CURRENT_TIMESTAMP,
                          post_id BIGINT NOT NULL,
                          user_id BIGINT NOT NULL,
                          FOREIGN KEY (post_id) REFERENCES Posts(id),
                          FOREIGN KEY (user_id) REFERENCES Users(id)
);

select * from roles;