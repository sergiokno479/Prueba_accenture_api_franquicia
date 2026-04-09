# imagen oficial de Java 21
FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

# El archivo compilado de la API
COPY target/*.jar app.jar

# Puerto 
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"] 