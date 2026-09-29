# Etapa 1: Render descarga Maven y compila tu proyecto omitiendo las pruebas
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Etapa 2: Render toma el archivo compilado y enciende tu servidor Java
FROM amazoncorretto:21-alpine-jdk
COPY --from=build /app/target/demoSI-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]