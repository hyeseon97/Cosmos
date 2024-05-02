# Java 17을 포함하는 베이스 이미지 사용
FROM openjdk:17-slim

# 필요한 도구 설치
RUN apt-get update && apt-get install -y maven

# Docker 소켓 마운트 (선택 사항)
VOLUME /var/run/docker.sock

# 프로젝트 디렉토리 설정
WORKDIR /app

# 소스 코드 복사
COPY . /app

# Maven을 사용하여 프로젝트 빌드
RUN mvn clean package -DskipTests

# 빌드 결과로 생성된 JAR 파일 복사
RUN cp target/*.jar app.jar

# 컨테이너 시작 시 Java 애플리케이션 실행
ENTRYPOINT ["java", "-jar", "app.jar"]