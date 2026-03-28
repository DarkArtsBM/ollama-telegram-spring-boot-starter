# Estágio de Build (compila o Java)
FROM maven:3.9.6-eclipse-temurin-21 AS build
COPY src .
RUN mvn clean package -DskipTests

# Estágio de Execução (roda o App)
FROM eclipse-temurin-21-jre
COPY --from=build /target/*.jar app.jar
# Variáveis de ambiente que vamos preencher no Compose
ENV BOT_TOKEN=""
ENV BOT_USERNAME=""
ENV DEEPSEEK_KEY=""

ENTRYPOINT ["java", "-jar", "/app.jar"]