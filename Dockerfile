FROM eclipse-temurin:21-jdk-alpine
COPY "./target/FUTBOL-1.jar" "app.jar"
EXPOSE 8211
ENTRYPOINT ["java", "-jar", "app.jar"]