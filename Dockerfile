FROM maven:3.8.2-jdk-11 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:11-jdk-slim
COPY --from=build /target/classes/com/example/tisoproject-0.0.1-SNAPSHOT.jar tisoproject.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","tisoproject.jar"]
