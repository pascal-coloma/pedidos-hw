# Stage 1: Build 
FROM openjdk:19-jdk AS build
WORKDIR /app

COPY pom.xml .
COPY src src
COPY mvnw .
COPY .mvn .mvn

RUN chmod +x ./mvnw
RUN ./mvnw clean package -DskipTests

# Stage 2: Runtime
FROM openjdk:19-jdk-slim
WORKDIR /app

# Copiar el JAR generado por Maven 
COPY target/hw-0.0.1-SNAPSHOT.jar app.jar

COPY env.properties .
COPY wallet/ ./wallet/


# Exponer el puerto
EXPOSE 8081

# Ejecutar la aplicación con las propiedades externas
ENTRYPOINT ["java", "-Dspring.config.import=file:env.properties", "-jar", "app.jar"]
