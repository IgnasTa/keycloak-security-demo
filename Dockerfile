FROM openjdk:21
WORKDIR /app
COPY target/security.demo.app-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8081
CMD ["java", "-jar", "app.jar"]