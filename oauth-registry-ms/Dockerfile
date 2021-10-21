FROM openjdk:11
#ARG JAR_FILE=target/oauth-registry-ms.jar
ADD target/oauth-registry-ms.jar oauth-registry-ms.jar
ENTRYPOINT ["java", "-jar", "oauth-registry-ms.jar"]
EXPOSE 8761