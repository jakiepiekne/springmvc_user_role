FROM maven:3.8.6-eclipse-temurin-17-alpine as builder

WORKDIR /app

COPY pom.xml /app/pom.xml

RUN mvn -f /app/pom.xml verify clean --fail-never

COPY src /app/src

RUN mvn -f /app/pom.xml package

FROM tomcat:9.0.68-jdk17

COPY --from=builder /app/target/ROOT.war /usr/local/tomcat/webapps/
EXPOSE 8080
CMD ["catalina.sh", "run"]



