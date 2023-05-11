FROM  openjdk:8-slim
EXPOSE 8889
ADD /target/configserver-0.0.1-SNAPSHOT.jar configserver-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "configserver-0.0.1-SNAPSHOT.jar"]