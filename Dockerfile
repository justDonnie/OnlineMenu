## Этап сборки
#FROM maven:3.8.5-openjdk-17 AS build
#COPY . .
#RUN mvn clean package -DskipTests
#
#FROM maven:3.8.4-openjdk-17-slim
#COPY --from=build /target/tisoproject-0.0.1-SNAPSHOT.jar tisoproject.jar
#EXPOSE 8080
#ENTRYPOINT ["java", "-jar", "tisoproject.jar"]

#FROM maven:3.8.2-jdk-11 AS build
#COPY . .
#RUN mvn clean package -Dmaven.test.skip=true
#
#FROM openjdk:11-jdk-slim
#COPY --from=build /target/*.jar tisoproject.jar
#EXPOSE 8080
#ENTRYPOINT ["java","-jar","tisoproject.jar"]

FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY . .
RUN mvn clean package -DskipTests

FROM maven:3.8.2-eclipse-temurin-17
COPY --from=build /target/TisoProject-0.0.1-SNAPSHOT.jar TisoProject.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","TisoProject.jar"]


#
#FROM maven:3.8.5-openjdk-17 AS build
#COPY . .
#RUN mvn clean package -DskipTests
#
#FROM openjdk:17.0.1-jdk-slim
#COPY --from=build /target/jolaman-0.0.1-SNAPSHOT.jar jolaman.jar
#EXPOSE 8080
#ENTRYPOINT ["java", "-jar", "jolaman.jar"]