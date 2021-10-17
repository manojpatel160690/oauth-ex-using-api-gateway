FROM openjdk:11
#EXPOSE 8081
ADD target/oauth-user-ms.jar oauth-user-ms.jar
# environment variable with default value
#ENV SPRING_PROFILE=dev "-Dspring.profiles.active=${SPRING_PROFILE}",
ENTRYPOINT ["java", "-jar", "oauth-user-ms.jar"]