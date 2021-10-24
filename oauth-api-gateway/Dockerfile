FROM openjdk:11-jre-slim
#ARG JAR_FILE=target/oauth-api-gateway.jar
ADD target/oauth-api-gateway.jar oauth-api-gateway.jar
EXPOSE 9191
ENTRYPOINT ["java", "-jar", "oauth-api-gateway.jar"]