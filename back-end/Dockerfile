FROM openjdk:17-jdk-slim
WORKDIR /app
COPY . .
RUN ./mvnw clean package -DskipTests  # Si usas Maven; para Gradle: ./gradlew build
EXPOSE 8080
CMD ["java", "-jar", "target/*.jar"]  # Ajusta al nombre de tu JAR
