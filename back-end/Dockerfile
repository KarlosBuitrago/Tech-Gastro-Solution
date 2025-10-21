FROM gradle:8.5-jdk17 AS builder
WORKDIR /home/gradle/project
COPY --chown=gradle:gradle . .
# Build the Spring Boot fat jar (bootJar)
RUN gradle clean bootJar --no-daemon -x test

FROM eclipse-temurin:17-jre-jammy
WORKDIR /app
COPY --from=builder /home/gradle/project/build/libs/*.jar app.jar

ENV JAVA_OPTS=""
# Render injects a PORT env var for web services. Default to 9000 if not set locally.
ENV PORT=9000

EXPOSE ${PORT}
ENTRYPOINT ["sh","-c","java $JAVA_OPTS -jar app.jar --server.port=${PORT}"]
