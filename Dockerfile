RUN mvn clean package -DskipTests
FROM openjdk:23
ADD target/demo-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
