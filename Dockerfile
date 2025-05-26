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
VOLUME /tmp

# Copy built jar
COPY --from=build /app/target/hw-0.0.1-SNAPSHOT.jar app.jar

# Add zip and unzip
COPY wallet.zip /app/wallet.zip
RUN apt-get update && apt-get install -y unzip && unzip /app/wallet.zip -d /app

# Expose port and start application
EXPOSE 8081
ENTRYPOINT ["java","-jar","app.jar"]


