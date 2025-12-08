# Java 21 환경의 기본 이미지 사용
FROM eclipse-temurin:21-jdk

# 빌드된 JAR 파일 경로
ARG JAR_FILE=build/libs/my-spring-boot-app-0.0.1-SNAPSHOT.jar

# 최종적으로 해당 jar를 app.jar라는 이름으로 복사
COPY ${JAR_FILE} app.jar

# 컨테이너 8080 포트 오픈 (Spring Boot 기본)
EXPOSE 8080

# 컨테이너가 실행될 때 Spring Boot JAR 실행
ENTRYPOINT ["java", "-jar", "/app.jar"]