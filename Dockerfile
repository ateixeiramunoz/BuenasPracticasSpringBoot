RUN mvn clean package -DskipTests
FROM openjdk:23
COPY target/myapp.jar /app/myapp.jar
ENTRYPOINT ["java", "-jar", "/app/myapp.jar"]