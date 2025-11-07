
# LionBoard - Spring Boot REST API 게시판

Spring Boot와 Spring Security를 활용한 RESTful API 기반 게시판 프로젝트입니다.

## 주요 기능

- 사용자 인증 및 권한 관리 (Basic Authentication)
- 게시글 CRUD
- 댓글 CRUD (소프트 삭제)
- 페이징 처리
- 역할 기반 접근 제어 (USER, ADMIN)

## 기술 스택

- Java 21
- Spring Boot 3.x
- Spring Security
- Spring Data JPA
- MySQL
- Lombok

## 프로젝트 구조

```
src/main/java/org/example/lionboard/
├── config/              # 설정 클래스
│   ├── DataInitializer.java
│   ├── SecurityConfig.java
│   └── CustomAuthenticationEntryPoint.java
├── controller/          # REST 컨트롤러
├── domain/              # 엔티티
├── dto/                 # 데이터 전송 객체
├── exception/           # 예외 처리
├── repository/          # JPA 레포지토리
├── security/            # 보안 관련
└── service/             # 비즈니스 로직
```

## 시작하기

### 1. 데이터베이스 설정

MySQL에서 다음 데이터베이스를 생성합니다.

```sql
CREATE DATABASE restdb;
```

### 2. 데이터베이스 연결 정보 수정

`src/main/resources/application.yml` 파일에서 본인의 환경에 맞게 수정합니다.

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/restdb
    username: spring
    password: spring1234
```

기본 설정값은 다음과 같습니다.
- 데이터베이스: `restdb`
- 사용자명: `spring`
- 비밀번호: `spring1234`

### 3. 테이블 생성

`src/main/resources/schema.sql` 파일의 SQL을 실행하여 테이블을 생성합니다.

```sql
-- users, roles, posts, comments, user_roles 테이블이 생성됩니다
```

### 4. 애플리케이션 실행

```bash
./gradlew bootRun
```

또는 IDE에서 `LionboardApplication.java`를 실행합니다.

### 5. 초기 데이터 확인

애플리케이션 실행 시 `DataInitializer`가 자동으로 초기 데이터를 생성합니다.

**생성되는 사용자**
- `user1:1234` (USER 권한)
- `user2:1234` (USER 권한)
- `admin:1234` (USER, ADMIN 권한)

**생성되는 데이터**
- 게시글 2개
- 댓글 5개

## API 테스트

`test/java/org/example/lionboard/test.http` 파일을 IntelliJ IDEA에서 열어 순서대로 실행하면서 테스트할 수 있습니다.

### 주요 API 엔드포인트

#### 사용자
- `POST /users` - 회원가입

#### 게시글
- `GET /posts` - 게시글 목록 조회 (페이징)
- `GET /posts/{id}` - 게시글 상세 조회
- `POST /posts` - 게시글 작성 (인증 필요)
- `PUT /posts/{id}` - 게시글 수정 (본인만 가능)
- `DELETE /posts/{id}` - 게시글 삭제 (본인만 가능)

#### 댓글
- `GET /posts/{postId}/comments` - 댓글 목록 조회
- `POST /posts/{postId}/comments` - 댓글 작성 (인증 필요)
- `DELETE /posts/{postId}/comments/{id}` - 댓글 삭제 (본인 또는 관리자)

### 인증 방법

Basic Authentication을 사용합니다.

```http
Authorization: Basic dXNlcjE6MTIzNA==
```

Base64 인코딩 값:
- `user1:1234` → `dXNlcjE6MTIzNA==`
- `user2:1234` → `dXNlcjI6MTIzNA==`
- `admin:1234` → `YWRtaW46MTIzNA==`

## 에러 응답 형식

모든 에러는 일관된 JSON 형식으로 응답됩니다.

```json
{
  "timestamp": "2025-11-07T14:30:00",
  "status": 404,
  "error": "Not Found",
  "message": "게시글을 찾을 수 없습니다.",
  "path": "/posts/999"
}
```

### 주요 HTTP 상태 코드
- `200 OK` - 성공
- `201 Created` - 생성 성공
- `204 No Content` - 삭제 성공
- `401 Unauthorized` - 인증 필요
- `403 Forbidden` - 권한 없음
- `404 Not Found` - 리소스 없음
- `409 Conflict` - 중복 데이터

## 라이센스

이 프로젝트는 교육 목적으로 작성되었습니다.
```