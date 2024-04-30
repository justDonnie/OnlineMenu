## Этап сборки
FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY target/*.jar TisoProject.jar
ENTRYPOINT ["java","-jar","TisoProject.jar"]
EXPOSE 8080