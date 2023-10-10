FROM maven:3-openjdk-17 as build-image
WORKDIR /to-build-app
COPY . .
RUN ["./mvnw clean package", "dependency:go-offline"]

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build-image /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-DJava.security.egd=file:dev/./urandom", "-jar", "/app/app.jar"]
