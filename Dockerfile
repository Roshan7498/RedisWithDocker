# ---------- Build stage ----------
FROM eclipse-temurin:21-jdk-jammy AS build
WORKDIR /src

# Copy Maven wrapper first and make it executable
COPY mvnw /src/mvnw
COPY .mvn /src/.mvn
RUN chmod +x /src/mvnw

# Copy the rest and build
COPY . /src
RUN ./mvnw -B -DskipTests package

# ---------- Runtime stage ----------
FROM eclipse-temurin:21-jre-jammy
USER root

# Optional but handy tools (mysql/redis CLI)
RUN apt-get update && \
    apt-get install -y --no-install-recommends ca-certificates curl mariadb-client redis-tools && \
    rm -rf /var/lib/apt/lists/*

WORKDIR /app

# Copy the built jar (use *.jar to avoid SNAPSHOT-only pattern)
COPY --from=build /src/target/*.jar /app/app.jar

ENTRYPOINT ["java","-jar","/app/app.jar"]
