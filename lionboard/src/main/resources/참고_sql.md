



### 참고

## 1. 데이터베이스 스키마 및 초기 데이터 설정
스프링 부트 프로젝트에서는 `src/main/resources` 경로에 SQL 파일을 배치하면 애플리케이션 시작 시 자동으로 실행됩니다.

## 파일 위치와 이름

다음과 같이 두 개의 파일로 분리하는 것이 표준입니다.

```
src/main/resources/
├── schema.sql
└── data.sql  
```

- `schema.sql`에는 테이블 생성 구문을 작성합니다.  (테이블을 생성하고, ddl-auto: none 으로 실행합니다. )
- `data.sql`에는 초기 데이터 삽입 구문을 작성합니다.  (이 프로젝트에서는 암호를 인코딩해서 입력해야하므로, CommandLineRunner를 이용해서 입력합니다.)
- 스프링 부트는 이 파일명을 자동으로 인식합니다.

## 실행 순서

스프링 부트는 다음 순서로 SQL 파일을 실행합니다.

먼저 `schema.sql`을 실행해서 테이블을 생성하고, 그 다음 `data.sql`을 실행해서 데이터를 삽입합니다. 이 순서는 자동으로 보장되므로 별도 설정이 필요 없습니다.

## 설정 방법

`application.properties` 또는 `application.yml`에 다음 설정을 추가합니다.

```properties
spring.sql.init.mode=always
spring.jpa.hibernate.ddl-auto=none
```

- `spring.sql.init.mode=always`는 SQL 파일을 항상 실행하도록 설정합니다.
- `spring.jpa.hibernate.ddl-auto=none`은 JPA가 자동으로 테이블을 생성하지 않도록 합니다.
- 이 설정이 없으면 embedded 데이터베이스에서만 실행됩니다.

## schema.sql 예제

```sql
CREATE TABLE IF NOT EXISTS roles (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE
);
```

- `CREATE TABLE IF NOT EXISTS`를 사용하면 이미 테이블이 있어도 오류가 발생하지 않습니다.
- 외래 키 제약조건은 테이블 생성 후에 추가합니다.

## data.sql 예제

```sql
INSERT INTO roles(name) VALUES ('USER'), ('ADMIN');

INSERT INTO users (name, login_id, password, email)
VALUES ('User1', 'user1', '1234', 'user1@example.com'),
       ('User2', 'user2', '1234', 'user2@example.com'),
       ('admin', 'admin', '1234', 'admin@example.com');
```

- 여러 행을 한 번에 삽입하면 성능이 좋습니다.
- 외래 키 참조가 있는 테이블은 참조되는 테이블 데이터를 먼저 삽입해야 합니다.

## 환경별 분리

개발과 운영 환경을 분리하려면 프로파일별 파일을 만들 수 있습니다.

```
src/main/resources/
├── schema.sql
├── data.sql
├── schema-dev.sql
├── data-dev.sql
├── schema-prod.sql
└── data-prod.sql
```

- `application-dev.properties`에서 `spring.profiles.active=dev`로 설정하면 `schema-dev.sql`과 `data-dev.sql`이 실행됩니다.
- 기본 파일은 항상 실행되고, 프로파일별 파일은 해당 프로파일에서만 추가로 실행됩니다.

## 주의사항

JPA를 사용하는 경우 `spring.jpa.hibernate.ddl-auto` 설정과 충돌할 수 있습니다. `ddl-auto`를 `none`으로 설정하거나, SQL 파일 대신 JPA가 스키마를 관리하도록 선택해야 합니다.
운영 환경에서는 `spring.sql.init.mode=never`로 설정하고, Flyway나 Liquibase 같은 마이그레이션 도구를 사용하는 것이 안전합니다.
