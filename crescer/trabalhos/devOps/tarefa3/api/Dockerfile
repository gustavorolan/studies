FROM openjdk:11-oracle
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} spring.jar
ENTRYPOINT ["java","-jar","/spring.jar"]