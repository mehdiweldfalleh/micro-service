FROM  openjdk:8-slim
EXPOSE 8090
ADD /target/Client-0.0.1-SNAPSHOT.jar Client-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "Client-0.0.1-SNAPSHOT.jar"]