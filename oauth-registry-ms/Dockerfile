FROM openjdk:11
#EXPOSE 8761
ADD target/oauth-registry-ms.jar oauth-registry-ms.jar
# environment variable with default value
#ENV SPRING_PROFILE=dev "-Dspring.profiles.active=${SPRING_PROFILE}",
ENTRYPOINT ["java", "-jar", "oauth-registry-ms.jar"]