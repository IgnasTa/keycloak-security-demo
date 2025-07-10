FROM openjdk:21
COPY target/security.demo.app-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8081
RUN chmod +x app.jar
CMD ["java", "-jar", "app.jar"]