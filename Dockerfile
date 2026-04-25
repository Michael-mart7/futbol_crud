FROM eclipse-temurin:21-jdk-alpine
COPY "./target/FUTBOL-1.jar" "app.jar"
EXPOSE 10000
ENTRYPOINT ["java", "-jar", "app.jar", "--server.port=${PORT:-10000}"]