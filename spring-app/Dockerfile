## 1. Base Image: JDK 21 사용 (가볍고 안정적인 eclipse-temurin 사용 권장)
#
#FROM eclipse-temurin:21-jdk-alpine
#
## 2. 작업 디렉토리 설정
#
#WORKDIR /app
#
## 3. 빌드된 JAR 파일을 Docker 이미지 내부로 복사
#
## 일반적으로 build/libs 경로에 생성됩니다. plain jar가 아닌 실행 가능한 jar를 복사해야 합니다.
#
#COPY build/libs/*-SNAPSHOT.jar app.jar
#
## 4. 컨테이너 실행 시 실행할 명령어
#
#ENTRYPOINT ["java", "-jar", "app.jar"]
# 첫번째 스테이지
FROM eclipse-temurin:21-jdk-alpine AS build
WORKDIR /app

COPY gradlew .
COPY gradle gradle
COPY build.gradle settings.gradle ./

RUN chmod +x ./gradlew

COPY src src

RUN ./gradlew clean build -x test --no-daemon

# 두번째 스테이지
FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app

COPY --from=build /app/build/libs/*-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]