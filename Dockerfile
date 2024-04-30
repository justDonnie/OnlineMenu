## Этап сборки
#FROM maven:3.8.5-openjdk-17 AS build
#COPY . .
#RUN mvn clean package -DskipTests
#
#FROM maven:3.8.4-openjdk-17-slim
#COPY --from=build /target/tisoproject-0.0.1-SNAPSHOT.jar tisoproject.jar
#EXPOSE 8080
#ENTRYPOINT ["java", "-jar", "tisoproject.jar"]

FROM eclipse-temurin:17-jdk-alpine AS build
COPY . .
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jdk-alpine
COPY --from=build /target/*.jar TisoProject.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","TisoProject.jar"]

#FROM eclipse-temurin:17-jdk-alpine
#VOLUME /tmp
#COPY target/*.jar TisoProject.jar
#ENTRYPOINT ["java","-jar","TisoProject.jar"]
#EXPOSE 8080