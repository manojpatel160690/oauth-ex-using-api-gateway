FROM openjdk:11
#EXPOSE 8081
ADD target/oauth-api-gateway.jar oauth-api-gateway.jar
# environment variable with default value
#ENV SPRING_PROFILE=dev "-Dspring.profiles.active=${SPRING_PROFILE}",
ENTRYPOINT ["java", "-jar", "oauth-api-gateway.jar"]