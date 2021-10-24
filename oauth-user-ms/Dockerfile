FROM openjdk:11-jre-slim
#ARG target/oauth-user-ms.jar
ADD target/oauth-user-ms.jar oauth-user-ms.jar
ENTRYPOINT ["java", "-jar", "oauth-user-ms.jar"]
EXPOSE 9002