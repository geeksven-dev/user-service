FROM openjdk:11-jdk-slim
ADD build/libs/userservice.jar userservice.jar
ENTRYPOINT [ "sh", "-c", "java -jar /userservice.jar" ]
