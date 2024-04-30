# Этап сборки
FROM maven:3.8.5-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17.0.1-jdk-slim
COPY --from=build /target/tisoproject-0.0.1-SNAPSHOT.jar tisoproject.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "jolaman.jar"]

#FROM maven:3.8.2-jdk-17 AS build
#WORKDIR /app
#COPY pom.xml .
#RUN mvn dependency:go-offline
#
#COPY src ./src
#RUN mvn package -DskipTests
#
## Этап развертывания
#FROM openjdk:11.0.14-jdk-slim
#WORKDIR /app
#COPY --from=build /app/target/*.jar tisoproject.jar
#EXPOSE 8080
#ENTRYPOINT ["java","-jar","tisoproject.jar"]


#FROM maven:3.8.2-jdk-11 AS build
#COPY . .
#RUN mvn clean package -Dmaven.test.skip=true
#
#FROM openjdk:11-jdk-slim
#COPY --from=build /target/*.jar tisoproject.jar
#EXPOSE 8080
#ENTRYPOINT ["java","-jar","tisoproject.jar"]
