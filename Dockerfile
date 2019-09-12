FROM openjdk:11-jdk-slim
ADD build/libs/user-service.jar user-service.jar
ENTRYPOINT [ "sh", "-c", "java -jar /user-service.jar" ]
