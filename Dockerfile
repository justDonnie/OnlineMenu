FROM maven:3.8.4-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM maven:3.8.4-openjdk-17-slim
COPY --from=build /target/TisoProject-0.0.1-SNAPSHOT.jar TisoProject.jar
EXPOSE 3030
ENTRYPOINT ["java", "-jar", "TisoProject.jar"]
